package br.com.stapassoli.springboot_order_microservice.service;

import br.com.stapassoli.springboot_order_microservice.config.RabbitMQConfig;
import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.exception.InvalidOrderItemDataException;
import br.com.stapassoli.springboot_order_microservice.exception.MissingOrderItemsException;
import br.com.stapassoli.springboot_order_microservice.mapper.OrderMapper;
import br.com.stapassoli.springboot_order_microservice.repository.OrderRepository;
import br.com.stapassoli.springboot_order_microservice.usecase.interfaces.OrderUseCase;
import br.com.stapassoli.springboot_order_microservice.validator.OrderStatusValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderUseCase orderUseCase;
    private final OrderMapper orderMapper;
    private final OrderStatusValidator orderStatusValidator;
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public OrderService(OrderUseCase orderUseCase, OrderMapper orderMapper, OrderStatusValidator orderStatusValidator, OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderUseCase = orderUseCase;
        this.orderMapper = orderMapper;
        this.orderStatusValidator = orderStatusValidator;
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        validateOrderRequest(orderRequestDTO);
        Order order = orderUseCase.saveOrder(orderRequestDTO);
        
        // Send message to RabbitMQ first
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.ORDER_EXCHANGE,
            RabbitMQConfig.ORDER_ROUTING_KEY,
            order
        );
        
        return order;
    }

    public Order findByOrderId(Long orderId) {
        return orderUseCase.getOrder(orderId);
    }

    public List<Order> findAllOrders() {
        return orderUseCase.findAllOrders();
    }

    public Order updateOrder(Long orderId, OrderRequestDTO orderRequestDTO) {
        validateOrderRequest(orderRequestDTO);
        Order existingOrder = orderUseCase.getOrder(orderId);
        Order updatedOrder = orderMapper.toEntity(orderRequestDTO);
        updatedOrder.setId(existingOrder.getId());
        
        orderUseCase.updateOrder(orderRequestDTO);
        
        return updatedOrder;
    }

    public void deleteOrder(Long orderId) {
        orderUseCase.deleteOrder(orderId);
    }

    public Order processOrder(Long orderId) {
        Order order = orderUseCase.getOrder(orderId);
        orderStatusValidator.validateForProcessing(order);
        orderUseCase.processOrder(orderId);
        return order;
    }

    public Order cancelOrder(Long orderId) {
        Order order = orderUseCase.getOrder(orderId);
        orderStatusValidator.validateForCancellation(order);
        orderUseCase.cancelOrder(orderId);
        return order;
    }

    public Order completeOrder(Long orderId) {
        Order order = orderUseCase.getOrder(orderId);
        orderStatusValidator.validateForCompletion(order);
        orderUseCase.completeOrder(orderId);
        return order;
    }

    private void validateOrderRequest(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO.getItems() == null || orderRequestDTO.getItems().isEmpty()) {
            throw new MissingOrderItemsException();
        }

        if (orderRequestDTO.getItems().stream().anyMatch(item -> 
            item.getProductId() == null || item.getProductId().trim().isEmpty() ||
            item.getQuantity() == null || item.getQuantity() <= 0 ||
            item.getUnitPrice() == null || item.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0
        )) {
            throw new InvalidOrderItemDataException();
        }
    }
}
