package br.com.stapassoli.springboot_order_microservice.usecase.impl;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.gateway.impl.OrderJpaGateway;
import br.com.stapassoli.springboot_order_microservice.usecase.interfaces.OrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderJpaGateway gateway;

    public OrderUseCaseImpl(OrderJpaGateway gateway) {
        this.gateway = gateway;
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
        return gateway.getOrder(orderId);
    }

}
