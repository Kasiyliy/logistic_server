package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.CompanyUser;
import kz.logistic.logistic_server.models.entities.User;

import java.util.List;


public interface CompanyUserService {

    CompanyUser findById(Long id) throws ServiceException;
    List<CompanyUser> findAll();
    List<CompanyUser> findAllWithDeleted();
    CompanyUser update(CompanyUser companyUser) throws ServiceException ;
    CompanyUser save(CompanyUser companyUser) throws ServiceException ;
    void delete(CompanyUser companyUser) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
    List<CompanyUser> findAllByCompany(Company company);
    CompanyUser findByCompanyAndUser(Company company, User user);
}
