package br.com.stapassoli.springboot_order_microservice.gateway.impl;

import br.com.stapassoli.springboot_order_microservice.dto.OrderItemDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.entity.OrderItem;
import br.com.stapassoli.springboot_order_microservice.enums.OrderStatus;
import br.com.stapassoli.springboot_order_microservice.exception.DuplicateOrderIdException;
import br.com.stapassoli.springboot_order_microservice.exception.OrderNotFoundException;
import br.com.stapassoli.springboot_order_microservice.gateway.interfaces.OrderGateway;
import br.com.stapassoli.springboot_order_microservice.mapper.OrderMapper;
import br.com.stapassoli.springboot_order_microservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderJpaGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderJpaGateway(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public Order saveOrder(OrderRequestDTO orderDTO) {
        if (orderRepository.existsByOrderId(orderDTO.getOrderId())) {
            throw new DuplicateOrderIdException();
        }

        Order order = orderMapper.toEntity(orderDTO);
        order.setStatus(OrderStatus.RECEIVED);
        order.setRequestDate(LocalDateTime.now());
        order.setTotalValue(calculateTotal(orderDTO.getItems()));

        List<OrderItem> orderItems = orderDTO.getItems().stream()
                .map(itemDTO -> {
                    OrderItem orderItem = orderMapper.toEntityWithoutOrder(itemDTO);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setItems(orderItems);

        return orderRepository.save(order);
    }

    private BigDecimal calculateTotal(List<OrderItemDTO> items) {
        return items.stream()
                .map(OrderItemDTO::getItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional
    public Order updateOrder(OrderRequestDTO orderDTO) {
        Order existingOrder = orderRepository.findByOrderId(orderDTO.getOrderId())
                .orElseThrow(OrderNotFoundException::new);
        
        existingOrder.setLastUpdatedAt(LocalDateTime.now());
        existingOrder.getItems().clear();
        
        // Create and set new order items
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemDTO.getProductId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(itemDTO.getUnitPrice());
            orderItem.setItemTotal(itemDTO.getItemTotal());
            orderItem.setOrder(existingOrder);
            existingOrder.getItems().add(orderItem);
        }
        
        // Update total value
        BigDecimal totalValue = orderDTO.getItems().stream()
                .map(OrderItemDTO::getItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        existingOrder.setTotalValue(totalValue);
        
        return orderRepository.save(existingOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException();
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public void processOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.PROCESSING);
        order.setProcessingStartDate(LocalDateTime.now());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.CANCELED);
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void completeOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.PROCESSED);
        order.setProcessingEndDate(LocalDateTime.now());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
