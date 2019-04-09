package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
