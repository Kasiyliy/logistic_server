package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.CarDto;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CarMapper extends AbstractModelMapper<Car, CarDto> {

    private ModelMapper modelMapper;
    private UserMapper userMapper;
    private CarTypeMapper carTypeMapper;

    @Autowired
    public CarMapper(ModelMapper modelMapper,
                     UserMapper userMapper,
                     CarTypeMapper carTypeMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.carTypeMapper = carTypeMapper;
    }

    @Override
    public CarDto toDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);
        if (car.getUser() != null) {
            carDto.setUser(userMapper.toDto(car.getUser()));
        }

        if (car.getCarType() != null) {
            carDto.setCarType(carTypeMapper.toDto(car.getCarType()));
        }
        return carDto;
    }

    @Override
    public Car toEntity(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        if (carDto.getUser() != null) {
            car.setUser(userMapper.toEntity(carDto.getUser()));
        }

        if (carDto.getCarType() != null) {
            car.setCarType(carTypeMapper.toEntity(carDto.getCarType()));
        }
        return car;
    }

    @Override
    public List<CarDto> toDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> toEntityList(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
