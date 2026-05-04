package com.yumka.demo.repository;


import com.yumka.demo.model.Payment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;





@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID>  {
     
    // 🔹 Pago por orden
    Optional<Payment> findByOrderId(UUID orderId);

    // 🔹 Pagos por usuario
    @Query("""
        SELECT p FROM Payment p
        WHERE p.order.user.id = :userId
        ORDER BY p.createdAt DESC
    """)
    List<Payment> findByUser(UUID userId);

    // 🔹 Pagos por productor
    @Query("""
        SELECT DISTINCT p FROM Payment p
        JOIN p.order o
        JOIN o.items oi
        JOIN oi.product pr
        WHERE pr.producer.id = :producerId
        ORDER BY p.createdAt DESC
    """)
    List<Payment> findByProducer(UUID producerId);

    // 🔹 Pagos por método
    List<Payment> findByMethod(String method);

    // 🔹 Pagos por estado
    List<Payment> findByStatus(String status);

    // 🔹 Pagos por rango de fechas
    @Query("""
        SELECT p FROM Payment p
        WHERE p.createdAt BETWEEN :start AND :end
    """)
    List<Payment> findBetweenDates(LocalDateTime start, LocalDateTime end);

    // 🔹 Total global
    @Query("""
        SELECT COALESCE(SUM(p.amount),0)
        FROM Payment p
        WHERE p.status = 'COMPLETED'
    """)
    Double totalRevenue();

    // 🔹 Total por usuario
    @Query("""
        SELECT COALESCE(SUM(p.amount),0)
        FROM Payment p
        WHERE p.order.user.id = :userId
        AND p.status = 'COMPLETED'
    """)
    Double totalByUser(UUID userId);

    // 🔹 Total por productor
    @Query("""
        SELECT COALESCE(SUM(oi.price * oi.quantity),0)
        FROM OrderItem oi
        JOIN oi.order o
        WHERE oi.product.producer.id = :producerId
        AND o.status IN ('PAID','DELIVERED')
    """)
    Double totalByProducer(UUID producerId);

    // 🔹 Total por método
    @Query("""
        SELECT p.method, SUM(p.amount)
        FROM Payment p
        GROUP BY p.method
    """)
    List<Object[]> totalByMethod();

    // 🔹 Conteo por estado
    @Query("""
        SELECT p.status, COUNT(p)
        FROM Payment p
        GROUP BY p.status
    """)
    List<Object[]> countByStatus();

    // 🔹 Validar si ya existe pago
    boolean existsByOrderId(UUID orderId);
}
