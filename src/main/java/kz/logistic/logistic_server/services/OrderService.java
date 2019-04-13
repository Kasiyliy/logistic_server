package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Order;
import kz.logistic.logistic_server.models.entities.User;

import java.util.List;


public interface OrderService {

    Order findById(Long id) throws ServiceException;
    List<Order> findAll();
    List<Order> findAllWithDeleted();
    Order update(Order order) throws ServiceException ;
    Order save(Order order) throws ServiceException ;
    void delete(Order order) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
