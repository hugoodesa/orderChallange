package br.com.stapassoli.springboot_order_microservice.gateway.impl;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.exception.OrderNotFoundException;
import br.com.stapassoli.springboot_order_microservice.gateway.interfaces.OrderGateway;
import br.com.stapassoli.springboot_order_microservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderJpaGateway implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderJpaGateway(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(String orderId, String productId, Integer quantity, String customerId) {

    }

    @Override
    public void updateOrder(Long orderId, String productId, Integer quantity) {

    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public void processOrder(Long orderId) {

    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public void completeOrder(Long orderId) {

    }

    @Override
    public Order getOrder(Long orderId) {
        return this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

}
