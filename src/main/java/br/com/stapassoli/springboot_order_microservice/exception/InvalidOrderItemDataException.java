package br.com.stapassoli.springboot_order_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderItemDataException extends RuntimeException{

    public InvalidOrderItemDataException() {
        super("Order item product ID is invalid");
    }
}
