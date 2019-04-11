package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByDeletedAtIsNull();

}
