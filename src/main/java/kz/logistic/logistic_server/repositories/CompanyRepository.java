package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findAllByDeletedAtIsNull();
}
