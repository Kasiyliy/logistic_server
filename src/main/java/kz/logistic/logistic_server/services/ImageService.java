package kz.logistic.logistic_server.services;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.Image;

import java.util.List;


public interface ImageService {

    Image findByItemId(Long id) throws ServiceException;
    Image save(Image image) throws ServiceException;
}
