package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.ItemDto;
import kz.logistic.logistic_server.models.entities.Item;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ItemMapper extends AbstractModelMapper<Item, ItemDto> {

    private ModelMapper modelMapper;
    private CompanyMapper companyMapper;

    @Autowired
    public ItemMapper(ModelMapper modelMapper,
                      CompanyMapper companyMapper) {
        this.modelMapper = modelMapper;
        this.companyMapper = companyMapper;

    }

    @Override
    public ItemDto toDto(Item item) {
        ItemDto itemDto = modelMapper.map(item, ItemDto.class);
        if (item.getCompany() != null) {
            itemDto.setCompany(companyMapper.toDto(item.getCompany()));
        }
        return itemDto;
    }

    @Override
    public Item toEntity(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        if (itemDto.getCompany() != null) {
            item.setCompany(companyMapper.toEntity(itemDto.getCompany()));
        }
        return item;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> items) {
        return items.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> toEntityList(List<ItemDto> itemDtos) {
        return itemDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
