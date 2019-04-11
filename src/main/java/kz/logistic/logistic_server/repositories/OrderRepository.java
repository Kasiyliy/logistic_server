package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByDeletedAtIsNull();
}
