package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.OrderStatus;

import java.util.List;


public interface OrderStatusService {

    OrderStatus findById(Long id) throws ServiceException;
    List<OrderStatus> findAll();
    List<OrderStatus> findAllWithDeleted();
    OrderStatus update(OrderStatus orderStatus) throws ServiceException ;
    OrderStatus save(OrderStatus orderStatus) throws ServiceException ;
    void delete(OrderStatus orderStatus) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
