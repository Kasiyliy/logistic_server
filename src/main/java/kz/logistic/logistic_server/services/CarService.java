package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Car;

import java.util.List;


public interface CarService {

    Car findById(Long id) throws ServiceException;
    List<Car> findAll();
    List<Car> findAllWithDeleted();
    Car update(Car car) throws ServiceException ;
    Car save(Car car) throws ServiceException ;
    void delete(Car car) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}
