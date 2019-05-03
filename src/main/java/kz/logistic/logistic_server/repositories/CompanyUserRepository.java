package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.CompanyUser;
import kz.logistic.logistic_server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser,Long> {

    List<CompanyUser> findAllByDeletedAtIsNull();
    List<CompanyUser> findAllByCompanyAndDeletedAtIsNull(Company company);
    CompanyUser findByCompanyAndUserAndDeletedAtIsNull(Company company, User user);
}
