package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.User;

import java.util.List;


public interface CompanyService {

    Company findById(Long id) throws ServiceException;
    List<Company> findAll();
    List<Company> findAllWithDeleted();
    Company update(Company Company) throws ServiceException ;
    Company getCompanyByUser(User user) throws ServiceException ;
    Company save(Company Company) throws ServiceException ;
    void delete(Company Company) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
