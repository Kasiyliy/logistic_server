package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.CarTypeDto;
import kz.logistic.logistic_server.models.entities.CarType;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CarTypeMapper extends AbstractModelMapper<CarType, CarTypeDto>{

    private ModelMapper modelMapper;

    @Autowired
    public CarTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CarTypeDto toDto(CarType carType) {
        return modelMapper.map(carType,CarTypeDto.class);
    }

    @Override
    public CarType toEntity(CarTypeDto carTypeDto) {
        return modelMapper.map(carTypeDto,CarType.class);
    }

    @Override
    public List<CarTypeDto> toDtoList(List<CarType> carTypes) {
        return carTypes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarType> toEntityList(List<CarTypeDto> carTypeDtos) {
        return carTypeDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
