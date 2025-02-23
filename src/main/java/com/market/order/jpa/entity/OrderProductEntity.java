package com.market.order.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_product")
@Schema(description = "Links products to orders")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the order-product link")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @Schema(description = "Identifier of the order")
    private OrderEntity order;

    @Column(name = "product_id", nullable = false)
    @Schema(description = "Identifier of the product")
    private Long productId;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "Quantity of the product in the order")
    private Integer quantity = 1;

    @Column(name = "price", nullable = false)
    @Schema(description = "Price of the product at the time of the order for 1 quantity")
    private BigDecimal price;
}