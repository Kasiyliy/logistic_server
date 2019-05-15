package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.repositories.CarRepository;
import kz.logistic.logistic_server.services.CarService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car findById(Long id) throws ServiceException {
        Optional<Car> carOptional = carRepository.findById(id);
        return carOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("car not found")
                .build());
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Car> findAllWithDeleted() {
        return carRepository.findAll();
    }

    @Override
    public Car update(Car car) throws ServiceException {
        if(car.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("car is null")
                    .build();
        }
        return  carRepository.save(car);
    }

    @Override
    public Car save(Car car) throws ServiceException {
        if(car.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("car already exists")
                    .build();
        }
        return  carRepository.save(car);
    }

    @Override
    public void delete(Car car) throws ServiceException {
        if(car.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("car is null")
                    .build();
        }
        car = findById(car.getId());
        car.setDeletedAt(new Date());
        carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Car car = findById(id);
        car.setDeletedAt(new Date());
        carRepository.save(car);
    }

    @Override
    public List<Car> findCarsByCompany(Long cId) throws ServiceException {
        return carRepository.findAllByCompanyIdAndDeletedAtIsNull(cId);
    }

    @Override
    public List<Car> findCarsByUser(Long  uId) throws ServiceException {
        return carRepository.findAllByUserIdAndDeletedAtIsNull(uId);
    }
}
