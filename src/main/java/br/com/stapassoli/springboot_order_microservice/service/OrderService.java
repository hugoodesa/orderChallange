package br.com.stapassoli.springboot_order_microservice.service;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.usecase.interfaces.OrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderUseCase orderUseCase;

    public OrderService(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    public Order findByOrderId(Long orderId) {
        return orderUseCase.getOrder(orderId);
    }


}
