import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {OrdersService} from "../../service/orders.service";
import {ActivatedRoute, NavigationEnd, Router, RouterLink} from "@angular/router";
import {Order, OrderType} from "../../model/order";
import {PaginationUtils} from "../../../common/dto/page-wrapper";
import {forkJoin, map, Observable, of, Subscription, switchMap} from "rxjs";
import {ErrorHandler} from "../../../common/error-handler.injectable";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {BrowseCourseService} from "../../../browse-course/service/browse-course.service";
import {shortUUID} from "../../../common/utils";

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './my-orders.component.html',
})
export class MyOrdersComponent implements OnInit, OnDestroy {

  orderService = inject(OrdersService);
  browseCourseService = inject(BrowseCourseService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  errorHandler = inject(ErrorHandler);

  orders: Order[] = [];
  paginationUtils?: PaginationUtils;
  navigationSubscription?: Subscription;

  ngOnInit(): void {
    this.loadData(0);

    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadData(0);
      }
    })
  }

  ngOnDestroy(): void {
    this.navigationSubscription!.unsubscribe();
  }


  onPageChange(pageNumber: number): void {
    if (pageNumber >= 0 && pageNumber < this.paginationUtils!.totalPages) {
      this.loadData(pageNumber);
    }
  }

  getPageRange(): number[] {
    return this.paginationUtils?.getPageRange() || [];
  }

  loadData(pageNumber: number): void {
    this.orderService.getAllOrders(pageNumber)
      .pipe(switchMap(pageWrapper => {
        this.paginationUtils = new PaginationUtils(pageWrapper.page);
        const orders = pageWrapper.content as Order[];
        return this.loadOrdersWithCourseDetails(orders);
      }))
      .subscribe({
        next: (ordersWithDetails) => {
          this.orders = ordersWithDetails;
        },
        error: (error) => {
          this.errorHandler.handleServerError(error.error);
        }
      })
  }

  private loadOrdersWithCourseDetails(orders: Order[]): Observable<Order[]> {
    // iterate over each order
    const orderObservables = orders.map(order => {
      return order.orderType === OrderType.PURCHASE
        ? this.loadOrderItemsWithDetails(order)
        : this.loadExchangeDetails(order)
    })
    return forkJoin(orderObservables);
  }

  private loadOrderItemsWithDetails(order: Order): Observable<Order> {
    const itemObservables = order.items.map(item => {
      return this.browseCourseService.getPublishedCourse(item.course)
        .pipe(map(course => ({...item, courseDetail: course})))
    });
    return forkJoin(itemObservables).pipe(map(itemsWithDetails => ({...order, items: itemsWithDetails})))
  }

  private loadExchangeDetails(order: Order) {
    if (order.exchangeDetails?.courseId) {
      return this.browseCourseService.getPublishedCourse(order.exchangeDetails.courseId)
        .pipe(map(course => ({
          ...order,
          exchangeDetails: {
            ...order.exchangeDetails,
            courseDetail: course
          }
        })))
    }
    return of(order);
  }

  getCourseIdAndTitle(order: Order): { id: number, title: string } | null {
    if (order.orderType === OrderType.PURCHASE && order.items.length > 0) {
      const { id, title } = order.items[0].courseDetail;
      return { id, title };
    }

    if (order.orderType === OrderType.EXCHANGE && order.exchangeDetails?.courseId) {
      const { courseDetail: { title, id } } = order.exchangeDetails;
      return { id, title };
    }
    return null;
  }

  protected readonly shortUUID = shortUUID;
}
