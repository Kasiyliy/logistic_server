package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.ItemOrder;

import java.util.List;


public interface ItemOrderService {

    ItemOrder findById(Long id) throws ServiceException;
    List<ItemOrder> findAll();
    List<ItemOrder> findAllWithDeleted();
    ItemOrder update(ItemOrder itemOrder) throws ServiceException ;
    ItemOrder save(ItemOrder itemOrder) throws ServiceException ;
    void delete(ItemOrder itemOrder) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
