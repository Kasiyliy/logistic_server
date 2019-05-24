package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog,Long>{
    List<OrderLog> findAllByDeletedAtIsNull();

    @Query(value = "SELECT id FROM order_logs where order_id = ?1 ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Long getLastByOrderId(Long orderId);
}
