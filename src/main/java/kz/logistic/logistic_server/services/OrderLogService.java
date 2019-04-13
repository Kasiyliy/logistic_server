package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.OrderLog;

import java.util.List;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
public interface OrderLogService {

    OrderLog findById(Long id) throws ServiceException;
    List<OrderLog> findAll();
    List<OrderLog> findAllWithDeleted();
    OrderLog update(OrderLog orderLog) throws ServiceException ;
    OrderLog save(OrderLog orderLog) throws ServiceException ;
    void delete(OrderLog orderLog) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
