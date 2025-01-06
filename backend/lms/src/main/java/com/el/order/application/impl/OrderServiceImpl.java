package com.el.order.application.impl;

import com.el.common.RolesBaseUtil;
import com.el.common.exception.AccessDeniedException;
import com.el.common.exception.ResourceNotFoundException;
import com.el.course.application.CourseQueryService;
import com.el.course.domain.Course;
import com.el.discount.application.DiscountService;
import com.el.order.application.OrderService;
import com.el.order.domain.*;
import com.el.order.web.dto.OrderItemDTO;
import com.el.order.web.dto.OrderRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.money.MonetaryAmount;
import java.util.*;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RolesBaseUtil rolesBaseUtil;
    private final DiscountService discountService;
    private final CourseQueryService courseQueryService;

    public OrderServiceImpl(OrderRepository orderRepository, RolesBaseUtil rolesBaseUtil, DiscountService discountService, CourseQueryService courseQueryService) {
        this.orderRepository = orderRepository;
        this.rolesBaseUtil = rolesBaseUtil;
        this.discountService = discountService;
        this.courseQueryService = courseQueryService;
    }

    public Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Order> findOrdersByCreatedBy(String createdBy, Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        if (rolesBaseUtil.isAdmin()) {
            return orderRepository.findAll(pageable).getContent();
        } else {
            return orderRepository.findAllByCreatedBy(createdBy, page, size);
        }
    }

    @Override
    public Order findOrderByCreatedByAndId(String createdBy, UUID id) {
        if (rolesBaseUtil.isAdmin()) {
            return orderRepository.findById(id)
                    .orElseThrow(ResourceNotFoundException::new);
        } else {
            return orderRepository.findByCreatedByAndId(createdBy, id)
                    .orElseThrow(ResourceNotFoundException::new);
        }
    }

    @Override
    public void paymentSucceeded(UUID orderId) {
        log.info("OrderService.paymentSucceeded: {}", orderId);

        Order order = findOrderById(orderId);
        order.makePaid();
        if (order.getDiscountCode() != null && !order.getDiscountCode().isBlank()) {
            discountService.increaseUsage(order.getDiscountCode());
        }
        orderRepository.save(order);
    }


    @Override
    @Transactional
    public void makeCancellingAllOrderAfterPaidForOrderPurchase(UUID orderId, String createdBy) {
        log.info("OrderService.makeCancellingAllOrderAfterPaidForOrderPurchase: {}", orderId);

        Order order = findOrderById(orderId);
        Long courseId = order.getACourseIdOnly();

        orderRepository.findAllByStatusAndCreatedBy(Status.PENDING, createdBy)
                .stream()
                .filter(o -> o.getOrderType() == OrderType.PURCHASE)
                .filter(o -> o.getACourseIdOnly().equals(courseId))
                .forEach(o -> {
                    o.cancelOrder();
                    orderRepository.save(o);
                });
    }

    @Override
    public void makeCancellingAllOrderAfterPaidForOrderExchange(UUID orderId, String createdBy) {
        log.info("OrderService.makeCancellingAllOrderAfterPaidForOrderExchange: {}", orderId);

        Order order = findOrderById(orderId);
        Long courseId = order.getExchangeDetails().courseId();

        orderRepository.findAllByStatusAndCreatedBy(Status.PENDING, createdBy)
                .stream()
                .filter(o -> o.getOrderType() == OrderType.EXCHANGE)
                .filter(o -> o.getExchangeDetails().courseId().equals(courseId))
                .forEach(o -> {
                    o.cancelOrder();
                    orderRepository.save(o);
                });
    }

    @Override
    public Order createOrder(String currentUsername, OrderRequestDTO orderRequestDTO) {
        log.info("OrderService.createOrder: {}, {}", currentUsername, orderRequestDTO);

        if (rolesBaseUtil.isAdmin() || rolesBaseUtil.isTeacher()) {
            throw new AccessDeniedException("Only authenticated users can create orders");
        }
        Set<OrderItem> items = new LinkedHashSet<>();

        for (OrderItemDTO itemDto : orderRequestDTO.items()) {
            Course course = courseQueryService.findPublishedCourseById(itemDto.id());
            items.add(new OrderItem(course.getId(), course.getPrice()));
        }

        Order newOrder = new Order(items);

        if (orderRequestDTO.discountCode() != null) {
            // Apply discount code
            MonetaryAmount monetaryAmount = discountService.calculateDiscount(
                    orderRequestDTO.discountCode(),
                    newOrder.getTotalPrice());
            newOrder.applyDiscount(monetaryAmount, orderRequestDTO.discountCode());
        }

        return orderRepository.save(newOrder);
    }

    public Order createOrderExchange(Long courseId, Long enrollmentId, MonetaryAmount additionalPrice) {
        log.info("OrderService.createOrderExchange: {}, {}, {}", courseId, enrollmentId, additionalPrice);

        if (rolesBaseUtil.isAdmin() || rolesBaseUtil.isTeacher()) {
            throw new AccessDeniedException("Only authenticated users can create orders");
        }

        Order newOrder = new Order(new ExchangeDetails(enrollmentId, courseId, additionalPrice));
        return orderRepository.save(newOrder);
    }

}
