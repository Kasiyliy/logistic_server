package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.ItemOrderDto;
import kz.logistic.logistic_server.models.entities.ItemOrder;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ItemOrderMapper extends AbstractModelMapper<ItemOrder, ItemOrderDto> {

    private ModelMapper modelMapper;
    private ItemMapper itemMapper;
    private OrderMapper orderMapper;

    @Autowired
    public ItemOrderMapper(ModelMapper modelMapper,
                           ItemMapper itemMapper,
                           OrderMapper orderMapper) {
        this.modelMapper = modelMapper;
        this.itemMapper = itemMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public ItemOrderDto toDto(ItemOrder itemOrder) {
        ItemOrderDto itemOrderDto = modelMapper.map(itemOrder, ItemOrderDto.class);
        if (itemOrder.getItem() != null) {
            itemOrderDto.setItem(itemMapper.toDto(itemOrder.getItem()));
        }

        if (itemOrder.getOrder() != null) {
            itemOrderDto.setOrder(orderMapper.toDto(itemOrder.getOrder()));
        }
        return itemOrderDto;
    }

    @Override
    public ItemOrder toEntity(ItemOrderDto itemOrderDto) {
        ItemOrder itemOrder = modelMapper.map(itemOrderDto, ItemOrder.class);

        if (itemOrderDto.getItem() != null) {
            itemOrder.setItem(itemMapper.toEntity(itemOrderDto.getItem()));
        }

        if (itemOrderDto.getOrder() != null) {
            itemOrder.setOrder(orderMapper.toEntity(itemOrderDto.getOrder()));
        }
        return itemOrder;
    }

    @Override
    public List<ItemOrderDto> toDtoList(List<ItemOrder> itemOrders) {
        return itemOrders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemOrder> toEntityList(List<ItemOrderDto> itemOrderDtos) {
        return itemOrderDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
