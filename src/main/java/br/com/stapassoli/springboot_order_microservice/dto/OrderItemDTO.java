package br.com.stapassoli.springboot_order_microservice.dto;

import java.math.BigDecimal;

public class OrderItemDTO {

    private String productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal itemTotal;

    public OrderItemDTO() {
    }

    public OrderItemDTO(String productId, Integer quantity, BigDecimal unitPrice, BigDecimal itemTotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.itemTotal = itemTotal;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
}
