package br.com.stapassoli.springboot_order_microservice.controller;

import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.mapper.OrderMapper;
import br.com.stapassoli.springboot_order_microservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = {"order", "orderList"}, allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDTO createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderService.createOrder(orderRequestDTO);
        return orderMapper.toDTO(order);
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrder(@PathVariable Long orderId) {
        Order order = orderService.findByOrderId(orderId);
        return orderMapper.toDTO(order);
    }

    @Cacheable(value = "orderList", key = "'all'")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = {"order", "orderList"}, allEntries = true)
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderService.updateOrder(orderId, orderRequestDTO);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @CacheEvict(value = {"order", "orderList"}, allEntries = true)
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

    @CacheEvict(value = {"order", "orderList"}, allEntries = true)
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @CacheEvict(value = {"order", "orderList"}, allEntries = true)
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<OrderDTO> completeOrder(@PathVariable Long orderId) {
        Order order = orderService.completeOrder(orderId);
        OrderDTO orderDTO = orderMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
