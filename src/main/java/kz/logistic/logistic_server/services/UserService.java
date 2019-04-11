package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.User;

import java.util.List;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
public interface UserService {

    User findById(Long id) throws ServiceException;
    List<User> findAll();
    List<User> findAllWithDeleted();
    User update(User user) throws ServiceException ;
    User save(User user) throws ServiceException ;
    void delete(User user) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
