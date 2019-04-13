package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.OrderStatusDto;
import kz.logistic.logistic_server.models.entities.OrderStatus;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderStatusMapper extends AbstractModelMapper<OrderStatus,OrderStatusDto> {

    private ModelMapper modelMapper;

    @Autowired
    public OrderStatusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderStatusDto toDto(OrderStatus orderStatus) {
        return modelMapper.map(orderStatus, OrderStatusDto.class);
    }

    @Override
    public OrderStatus toEntity(OrderStatusDto orderStatusDto) {
        return modelMapper.map(orderStatusDto, OrderStatus.class);
    }

    @Override
    public List<OrderStatusDto> toDtoList(List<OrderStatus> orderStatuses) {
        return orderStatuses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderStatus> toEntityList(List<OrderStatusDto> orderStatusDtos) {
        return  orderStatusDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
