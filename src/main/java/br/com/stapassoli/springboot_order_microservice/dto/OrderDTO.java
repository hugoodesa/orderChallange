package br.com.stapassoli.springboot_order_microservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;
    private String orderId;
    private LocalDateTime requestDate;
    private List<OrderItemDTO> items;
    private BigDecimal totalValue;
    private String status;
    private LocalDateTime processingStartDate;
    private LocalDateTime processingEndDate;
    private LocalDateTime lastUpdatedAt;

    public OrderDTO() {}

    public OrderDTO(Long id ,String orderId, LocalDateTime requestDate, List<OrderItemDTO> items, BigDecimal totalValue, String status, LocalDateTime processingStartDate, LocalDateTime processingEndDate, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.requestDate = requestDate;
        this.items = items;
        this.totalValue = totalValue;
        this.status = status;
        this.processingStartDate = processingStartDate;
        this.processingEndDate = processingEndDate;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getProcessingStartDate() {
        return processingStartDate;
    }

    public void setProcessingStartDate(LocalDateTime processingStartDate) {
        this.processingStartDate = processingStartDate;
    }

    public LocalDateTime getProcessingEndDate() {
        return processingEndDate;
    }

    public void setProcessingEndDate(LocalDateTime processingEndDate) {
        this.processingEndDate = processingEndDate;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
