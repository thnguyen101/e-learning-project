import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {CourseService} from "../../service/course.service";
import {ErrorHandler} from "../../../../common/error-handler.injectable";
import {Course} from "../../model/view/course";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputRowComponent} from "../../../../common/input-row/input-row.component";

@Component({
  selector: 'app-set-price',
  standalone: true,
  imports: [
    RouterLink,
    InputRowComponent,
    ReactiveFormsModule
  ],
  templateUrl: './set-price.component.html',
})
export class SetPriceComponent implements OnInit {

  route = inject(ActivatedRoute);
  router = inject(Router);
  courseService = inject(CourseService);
  errorHandler = inject(ErrorHandler)

  courseId?: number;
  requestId?: number;
  course?: Course;

  ngOnInit(): void {
    this.courseId = this.route.snapshot.params['courseId'];
    const requestIdParam = this.route.snapshot.queryParamMap.get('requestId')
    this.requestId = requestIdParam ? +requestIdParam : undefined;
    this.courseService.getCourse(this.courseId!).subscribe({
      next: data => this.course = data,
      error: error => this.errorHandler.handleServerError(error.error)
    })
  }

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: `Course was updated price successfully.`
    };
    return messages[key];
  }

  currenciesMap: Record<string, string> = {
    VND: 'VND',
    USD: 'USD',
  }

  setForm = new FormGroup({
    price: new FormControl(null, [Validators.required]),
    currency: new FormControl(null, [Validators.required]),
  })

  handleSubmit() {
    window.scrollTo(0, 0);
    this.setForm.markAllAsTouched();
    if (!this.setForm.valid) {
      return;
    }

    const number = this.setForm.get('price')?.value;
    const currency = this.setForm.get('currency')?.value;
    const price = currency + '' + number;

    if (this.courseId !== undefined && this.requestId !== undefined) {
      this.courseService.updatePrice(this.courseId!, price).subscribe({
        next: () => this.router.navigate(['/administration/courses', this.courseId, 'requests', 'approve', this.requestId], {
          state: {
            msgSuccess: this.getMessage('updated')
          }
        }),
        error: (error) => this.errorHandler.handleServerError(error.error, this.setForm, this.getMessage)
      })
    }

  }

}
