package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findAllByDeletedAtIsNull();

}
