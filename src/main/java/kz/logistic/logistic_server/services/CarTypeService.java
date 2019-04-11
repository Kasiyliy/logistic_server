package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.CarType;

import java.util.List;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
public interface CarTypeService {

    CarType findById(Long id) throws ServiceException;
    List<CarType> findAll();
    List<CarType> findAllWithDeleted();
    CarType update(CarType carType) throws ServiceException ;
    CarType save(CarType carType) throws ServiceException ;
    void delete(CarType carType) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
