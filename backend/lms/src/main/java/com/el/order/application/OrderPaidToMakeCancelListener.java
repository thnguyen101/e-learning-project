package com.el.order.application;

import com.el.order.domain.Order;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidToMakeCancelListener {

    private final OrderService orderService;

    public OrderPaidToMakeCancelListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApplicationModuleListener
    public void makeCancelAllOrderPendingForOrderPurchasePaid(Order.OrderPaidEvent e) {
        orderService.makeCancellingAllOrderAfterPaidForOrderPurchase(e.id(), e.createdBy());
    }

    @ApplicationModuleListener
    public void makeCancelAllOrderPendingForOrderExchangePaid(Order.OrderExchangePaidEvent e) {
        orderService.makeCancellingAllOrderAfterPaidForOrderExchange(e.id(), e.createdBy());
    }

}
