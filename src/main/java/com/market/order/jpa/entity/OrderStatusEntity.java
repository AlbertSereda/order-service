package com.market.order.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_status")
@Schema(description = "Order statuses")
public class OrderStatusEntity {

    @Id
    @Column(name = "id")
    @Schema(description = "Unique identifier for the order status")
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    @Schema(description = "Name of the order status")
    private String name;
}
