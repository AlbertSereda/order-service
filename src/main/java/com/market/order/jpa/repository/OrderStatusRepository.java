package com.market.order.jpa.repository;

import com.market.order.jpa.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {

}
