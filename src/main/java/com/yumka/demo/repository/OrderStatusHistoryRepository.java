package com.yumka.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yumka.demo.model.OrderStatusHistory;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, UUID>{
    
    @Query("""
        SELECT h FROM OrderStatusHistory h
        WHERE h.order.id = :orderId
        ORDER BY h.changedAt ASC
    """)
    List<OrderStatusHistory> findByOrderId(UUID orderId);
}
