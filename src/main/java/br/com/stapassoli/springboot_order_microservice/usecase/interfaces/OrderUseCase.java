package br.com.stapassoli.springboot_order_microservice.usecase.interfaces;

import br.com.stapassoli.springboot_order_microservice.entity.Order;

public interface OrderUseCase {

    void saveOrder(String orderId, String productId, Integer quantity, String customerId);

    void updateOrder(Long orderId, String productId, Integer quantity);

    void deleteOrder(Long orderId);

    void processOrder(Long orderId);

    void cancelOrder(Long orderId);

    void completeOrder(Long orderId);

    Order getOrder(Long orderId);

}
