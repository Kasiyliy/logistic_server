package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.OrderLogDto;
import kz.logistic.logistic_server.models.entities.OrderLog;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderLogMapper extends AbstractModelMapper<OrderLog, OrderLogDto> {

    private ModelMapper modelMapper;
    private OrderMapper orderMapper;

    @Autowired
    public OrderLogMapper(ModelMapper modelMapper,
                          OrderMapper orderMapper) {
        this.modelMapper = modelMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderLogDto toDto(OrderLog orderLog) {
        OrderLogDto orderLogDto = modelMapper.map(orderLog, OrderLogDto.class);

        if (orderLog.getOrder() != null) {
            orderLogDto.setOrderDto(orderMapper.toDto(orderLog.getOrder()));
        }

        return orderLogDto;
    }

    @Override
    public OrderLog toEntity(OrderLogDto orderLogDto) {
        OrderLog orderLog = modelMapper.map(orderLogDto, OrderLog.class);

        if (orderLogDto.getOrderDto() != null) {
            orderLog.setOrder(orderMapper.toEntity(orderLogDto.getOrderDto()));
        }

        return orderLog;
    }

    @Override
    public List<OrderLogDto> toDtoList(List<OrderLog> orderLogs) {
        return orderLogs.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderLog> toEntityList(List<OrderLogDto> orderLogDtos) {
        return orderLogDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
