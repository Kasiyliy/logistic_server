package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{
    List<OrderStatus> findAllByDeletedAtIsNull();
}
