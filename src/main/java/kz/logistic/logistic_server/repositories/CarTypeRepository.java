package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long>{
}
