package br.com.stapassoli.springboot_order_microservice.service;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.enums.OrderStatus;
import br.com.stapassoli.springboot_order_microservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderMessageListener {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public OrderMessageListener(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void processOrdersFromQueue() {
        System.out.println("Starting scheduled order processing");
        
        Message message = rabbitTemplate.receive("order.queue");
        
        if (message != null) {
            try {
                Order order = (Order) rabbitTemplate.getMessageConverter().fromMessage(message);
                System.out.println(String.format("Processing order from queue: %s", order.getId()));
                
                order.setProcessingStartDate(LocalDateTime.now());
                order.setStatus(OrderStatus.PROCESSING);
                
                order = orderRepository.save(order);
                System.out.println(String.format("Order processing started: %s", order.getId()));
                
                Thread.sleep(2000);

                order.setProcessingEndDate(LocalDateTime.now());
                order.setStatus(OrderStatus.COMPLETED);
                
                order = orderRepository.save(order);
                System.out.println(String.format("Order processing completed: %s", order.getId()));
                
            } catch (Exception e) {
                System.out.println(String.format("Error processing order: %s", e.getMessage()));
            }
        } else {
            System.out.println("No orders to process in the queue");
        }
    }
} 