package br.com.stapassoli.springboot_order_microservice.validator;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.enums.OrderStatus;
import br.com.stapassoli.springboot_order_microservice.exception.InvalidOrderStatusException;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusValidator {

    public void validateForProcessing(Order order) {
        if (order.getStatus() != OrderStatus.RECEIVED) {
            throw new InvalidOrderStatusException("Order can only be processed when in RECEIVED status");
        }
    }

    public void validateForCancellation(Order order) {
        if (order.getStatus() == OrderStatus.CANCELED || order.getStatus() == OrderStatus.PROCESSED) {
            throw new InvalidOrderStatusException("Order cannot be canceled when already CANCELED or PROCESSED");
        }
    }

    public void validateForCompletion(Order order) {
        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new InvalidOrderStatusException("Order can only be completed when in PROCESSING status");
        }
    }
} 