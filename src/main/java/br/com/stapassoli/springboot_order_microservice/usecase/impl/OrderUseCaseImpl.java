package br.com.stapassoli.springboot_order_microservice.usecase.impl;

import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.gateway.impl.OrderJpaGateway;
import br.com.stapassoli.springboot_order_microservice.usecase.interfaces.OrderUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderJpaGateway gateway;

    public OrderUseCaseImpl(OrderJpaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Order saveOrder(OrderRequestDTO orderDTO) {
        return gateway.saveOrder(orderDTO);
    }

    @Override
    public Order updateOrder(OrderRequestDTO orderDTO) {
        return gateway.updateOrder(orderDTO);
    }

    @Override
    public void deleteOrder(Long orderId) {
        gateway.deleteOrder(orderId);
    }

    @Override
    public void processOrder(Long orderId) {
        gateway.processOrder(orderId);
    }

    @Override
    public void cancelOrder(Long orderId) {
        gateway.cancelOrder(orderId);
    }

    @Override
    public void completeOrder(Long orderId) {
        gateway.completeOrder(orderId);
    }

    @Override
    public Order getOrder(Long orderId) {
        return gateway.getOrder(orderId);
    }

    public List<Order> findAllOrders() {
        return gateway.findAllOrders();
    }
}
