package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Item;

import java.util.List;


public interface ItemService {

    Item findById(Long id) throws ServiceException;
    List<Item> findAll();
    List<Item> findAllWithDeleted();
    Item update(Item item) throws ServiceException ;
    Item save(Item item) throws ServiceException ;
    void delete(Item item) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
