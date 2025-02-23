package com.market.order.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "order")
@Schema(description = "Order information")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the order")
    private Long id;

    @Column(name = "user_id", nullable = false)
    @Schema(description = "Identifier of the user who placed the order")
    private Long userId;

    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "Name of the order")
    private String name;

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    @Schema(description = "Identifier of the order status")
    private OrderStatusEntity orderStatus;

    @Column(name = "cart_id", nullable = false)
    @Schema(description = "Identifier of the cart associated with the order")
    private Long cartId;

    @Column(name = "total_price", nullable = false)
    @Schema(description = "Total price of the order")
    private BigDecimal totalPrice;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    @Schema(description = "Date of creation")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    @Schema(description = "Date of the last update")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "is_archive", nullable = false)
    @Schema(description = "Archive status of the order")
    private Boolean isArchive = false;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Schema(description = "Products in the order")
    private Set<OrderProductEntity> orderProducts;
}