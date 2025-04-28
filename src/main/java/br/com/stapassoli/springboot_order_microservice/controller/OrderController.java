package br.com.stapassoli.springboot_order_microservice.controller;

import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.mapper.OrderMapper;
import br.com.stapassoli.springboot_order_microservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
        Order order = orderService.findByOrderId(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

}
