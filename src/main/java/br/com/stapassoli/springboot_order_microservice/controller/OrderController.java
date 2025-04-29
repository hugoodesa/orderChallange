package br.com.stapassoli.springboot_order_microservice.controller;

import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.mapper.OrderMapper;
import br.com.stapassoli.springboot_order_microservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderService.createOrder(orderRequestDTO);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
        Order order = orderService.findByOrderId(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderService.updateOrder(orderId, orderRequestDTO);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{orderId}/process")
    public ResponseEntity<OrderDTO> processOrder(@PathVariable Long orderId) {
        Order order = orderService.processOrder(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<OrderDTO> completeOrder(@PathVariable Long orderId) {
        Order order = orderService.completeOrder(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
