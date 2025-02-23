package com.market.order.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_status_history")
@Schema(description = "Stores order status change history")
public class OrderStatusHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the order status change record")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @Schema(description = "Identifier of the order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "old_status_id", nullable = false)
    @Schema(description = "Previous status of the order")
    private OrderStatusEntity oldStatus;

    @Column(name = "change_date", nullable = false)
    @CreationTimestamp
    @Schema(description = "Date of the status change")
    private LocalDateTime changeDate = LocalDateTime.now();
}
