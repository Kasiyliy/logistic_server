package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;

import java.util.List;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
public interface CompanyService {

    Company findById(Long id) throws ServiceException;
    List<Company> findAll();
    List<Company> findAllWithDeleted();
    Company update(Company Company) throws ServiceException ;
    Company save(Company Company) throws ServiceException ;
    void delete(Company Company) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
