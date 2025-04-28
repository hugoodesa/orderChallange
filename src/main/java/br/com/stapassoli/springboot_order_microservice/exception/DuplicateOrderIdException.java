package br.com.stapassoli.springboot_order_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateOrderIdException extends RuntimeException {

    public DuplicateOrderIdException(Long orderId) {
        super(String.format("Order with ID %s already exists.", orderId));
    }

}
