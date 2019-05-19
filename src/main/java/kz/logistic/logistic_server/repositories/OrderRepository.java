package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByDeletedAtIsNull();

    @Query("select o from Order o join o.status s join o.client c " +
            "where o.deletedAt is null " +
            "and s.id not in (1,2) " +
            "and c.id = ?1 " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndClientId(Long clientId);

    @Query("select o from Order o join o.status s join o.driver d " +
            "where o.deletedAt is null " +
            "and s.id not in (1,2) " +
            "and d.id = ?1  " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndDriverId(Long driverId);

    @Query("select o from Order o join o.status s join o.company c " +
            "where o.deletedAt is null " +
            "and s.id not in (1,2) " +
            "and c.id = ?1 " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndCompanyId(Long companyId);


    @Query("select o from Order o join o.status s join o.client c " +
            "where o.deletedAt is null " +
            "and s.id in (1,2) " +
            "and c.id = ?1 " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndClientIdHistory(Long clientId);

    @Query("select o from Order o join o.status s join o.driver d " +
            "where o.deletedAt is null " +
            "and s.id in (1,2) " +
            "and d.id = ?1  " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndDriverIdHistory(Long driverId);

    @Query("select o from Order o join o.status s join o.company c " +
            "where o.deletedAt is null " +
            "and s.id in (1,2) " +
            "and c.id = ?1 " +
            "order by o.id")
    List<Order> findAllByDeletedAtIsNullAndCompanyIdHistory(Long companyId);
}
