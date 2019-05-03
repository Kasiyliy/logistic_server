package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.Item;
import kz.logistic.logistic_server.repositories.ItemRepository;
import kz.logistic.logistic_server.services.ItemService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findById(Long id) throws ServiceException {
        Optional<Item> itemOptional = itemRepository.findById(id);
        return itemOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("item not found")
                .build());
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Item> findAllWithDeleted() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllByCompany(Company company) {
        return itemRepository.findAllByCompanyAndDeletedAtIsNull(company);
    }

    @Override
    public Item update(Item item) throws ServiceException {
        if(item.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("item is null")
                    .build();
        }
        return  itemRepository.save(item);
    }

    @Override
    public Item save(Item item) throws ServiceException {
        if(item.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("item already exists")
                    .build();
        }
        return  itemRepository.save(item);
    }

    @Override
    public void delete(Item item) throws ServiceException {
        if(item.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("item is null")
                    .build();
        }
        item = findById(item.getId());
        item.setDeletedAt(new Date());
        itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Item item = findById(id);
        item.setDeletedAt(new Date());
        itemRepository.save(item);
    }
}
