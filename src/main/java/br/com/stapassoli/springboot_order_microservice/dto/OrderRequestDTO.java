package br.com.stapassoli.springboot_order_microservice.dto;

import br.com.stapassoli.springboot_order_microservice.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequestDTO {

    private String orderId;
    private LocalDateTime requestDate;
    private List<OrderItemDTO> items;
    private BigDecimal totalValue;
    private OrderStatus status;
    private LocalDateTime processingStartDate;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(String orderId, List<OrderItemDTO> items) {
        this.orderId = orderId;
        this.requestDate = LocalDateTime.now();
        this.items = items;
        this.totalValue = calculateTotal(items);
        this.status = OrderStatus.PROCESSING;
    }

    private BigDecimal calculateTotal(List<OrderItemDTO> items) {
        return items.stream().map(OrderItemDTO::getItemTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getProcessingStartDate() {
        return processingStartDate;
    }

    public void setProcessingStartDate(LocalDateTime processingStartDate) {
        this.processingStartDate = processingStartDate;
    }


}
