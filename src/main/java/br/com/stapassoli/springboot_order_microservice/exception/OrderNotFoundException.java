package br.com.stapassoli.springboot_order_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super(String.format("Order not found with id: %s", orderId));
    }

    public OrderNotFoundException() {
        super("Order not found");
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
