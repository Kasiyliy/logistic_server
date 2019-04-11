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
public class CarMapper extends AbstractModelMapper<Car,CarDto> {

    private ModelMapper modelMapper;

    @Autowired
    public CarMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CarDto toDto(Car car) {
        return modelMapper.map(car, CarDto.class);
    }

    @Override
    public Car toEntity(CarDto carDto) {
        return modelMapper.map(carDto, Car.class);
    }

    @Override
    public List<CarDto> toDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> toEntityList(List<CarDto> carDtos) {
        return  carDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
