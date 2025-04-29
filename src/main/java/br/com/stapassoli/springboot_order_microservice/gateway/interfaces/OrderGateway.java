package br.com.stapassoli.springboot_order_microservice.gateway.interfaces;

import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;

import java.util.List;

public interface OrderGateway {

    Order saveOrder(OrderRequestDTO orderDTO);

    Order updateOrder(OrderRequestDTO orderDTO);

    void deleteOrder(Long orderId);

    void processOrder(Long orderId);

    void cancelOrder(Long orderId);

    void completeOrder(Long orderId);

    Order getOrder(Long orderId);

    List<Order> findAllOrders();
}
