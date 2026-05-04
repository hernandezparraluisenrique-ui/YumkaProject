package com.yumka.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("""
        SELECT o FROM Order o
        WHERE o.user.id = :userId
        ORDER BY o.createdAt DESC
    """)
    List<Order> findByUser(@Param("userId") UUID userId);

    @Query("""
        SELECT o FROM Order o
        WHERE o.status = :status
        ORDER BY o.createdAt DESC
    """)
    List<Order> findByStatus(@Param("status") String status);

    @Query("""
        SELECT o FROM Order o
        WHERE o.createdAt BETWEEN :start AND :end
    """)
    List<Order> findBetweenDates(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        SELECT COALESCE(SUM(o.total), 0)
        FROM Order o
        WHERE o.status IN ('PAID','DELIVERED')
    """)
    Double totalRevenue();

    @Query("""
        SELECT o.status, COUNT(o)
        FROM Order o
        GROUP BY o.status
    """)
    List<Object[]> countByStatus();

    @Query("""
        SELECT DISTINCT o FROM Order o
        JOIN o.items oi
        JOIN oi.product p
        WHERE p.producer.id = :producerId
    """)
    List<Order> findByProducer(@Param("producerId") UUID producerId);
    
}
