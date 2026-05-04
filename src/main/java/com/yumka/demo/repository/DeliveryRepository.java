package com.yumka.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yumka.demo.model.Delivery;
import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    Optional<Delivery> findByOrderId(UUID orderId);

    List<Delivery> findByStatus(String status);

    @Query("""
        SELECT d FROM Delivery d
        WHERE d.status = 'PENDING'
    """)
    List<Delivery> findPending();

    @Query("""
        SELECT d FROM Delivery d
        WHERE d.status = 'IN_TRANSIT'
    """)
    List<Delivery> findInTransit();

    @Query("""
        SELECT d.status, COUNT(d)
        FROM Delivery d
        GROUP BY d.status
    """)
    List<Object[]> countByStatus();
}
