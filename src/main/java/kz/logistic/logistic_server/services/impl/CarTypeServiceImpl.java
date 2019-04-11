package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.CarType;
import kz.logistic.logistic_server.repositories.CarTypeRepository;
import kz.logistic.logistic_server.services.CarTypeService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
public class CarTypeServiceImpl implements CarTypeService{

    private CarTypeRepository carTypeRepository;

    public CarTypeServiceImpl(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    @Override
    public CarType findById(Long id) throws ServiceException {
        return carTypeRepository.findById(id)
                .orElseThrow(() -> ServiceException.builder()
                        .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                        .message("car type not found").build());
    }

    @Override
    public List<CarType> findAll() {
        return carTypeRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<CarType> findAllWithDeleted() {
        return carTypeRepository.findAll();
    }

    @Override
    public CarType update(CarType carType) throws ServiceException {

        if(carType.getId()==null){
            throw ServiceException.builder().errorCode(ErrorCode.RESOURCE_NOT_FOUND).message("car type not found").build();
        }
        try{
            CarType updatingCarType = findById(carType.getId());
            carType.setDeletedAt(updatingCarType.getDeletedAt());
            carType.setCreatedAt(updatingCarType.getCreatedAt());
        }catch (ServiceException se){
            throw ServiceException.builder().errorCode(ErrorCode.INVALID_ARGUMENT).message("car type already exists").build();
        }

        return carTypeRepository.save(carType);
    }

    @Override
    public CarType save(CarType carType) throws ServiceException {
        if(carType.getId()!=null){
            throw ServiceException.builder().errorCode(ErrorCode.ALREADY_EXISTS).message("car type already exists").build();
        }
        return carTypeRepository.save(carType);
    }

    @Override
    public void delete(CarType carType) throws ServiceException {
        if(carType.getId()==null){
            throw ServiceException.builder().errorCode(ErrorCode.RESOURCE_NOT_FOUND).message("car type not found").build();
        }
        carTypeRepository.delete(carType);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        carTypeRepository.deleteById(id);
    }
}
