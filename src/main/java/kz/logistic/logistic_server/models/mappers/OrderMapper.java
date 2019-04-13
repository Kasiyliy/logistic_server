package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.OrderDto;
import kz.logistic.logistic_server.models.entities.Order;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderMapper extends AbstractModelMapper<Order, OrderDto> {

    private ModelMapper modelMapper;
    private UserMapper userMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper,
                       UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);

        if (order.getClient() != null) {
            orderDto.setClient(userMapper.toDto(order.getClient()));
        }
        if (order.getDriver() != null) {
            orderDto.setDriver(userMapper.toDto(order.getDriver()));
        }

        return orderDto;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        if (orderDto.getClient() != null) {
            order.setClient(userMapper.toEntity(orderDto.getClient()));
        }
        if (orderDto.getDriver() != null) {
            order.setDriver(userMapper.toEntity(orderDto.getDriver()));
        }

        return order;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> orderDtos) {
        return orderDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
