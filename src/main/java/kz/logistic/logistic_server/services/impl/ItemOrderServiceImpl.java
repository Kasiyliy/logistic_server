package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.ItemOrder;
import kz.logistic.logistic_server.repositories.ItemOrderRepository;
import kz.logistic.logistic_server.services.ItemOrderService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ItemOrderServiceImpl implements ItemOrderService {

    private ItemOrderRepository itemOrderRepository;

    @Autowired
    public ItemOrderServiceImpl(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    @Override
    public ItemOrder findById(Long id) throws ServiceException {
        Optional<ItemOrder> itemOrderOptional = itemOrderRepository.findById(id);
        return itemOrderOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("itemOrder not found")
                .build());
    }

    @Override
    public List<ItemOrder> findAll() {
        return itemOrderRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<ItemOrder> findAllWithDeleted() {
        return itemOrderRepository.findAll();
    }

    @Override
    public ItemOrder update(ItemOrder itemOrder) throws ServiceException {
        if(itemOrder.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("itemOrder is null")
                    .build();
        }
        return  itemOrderRepository.save(itemOrder);
    }

    @Override
    public ItemOrder save(ItemOrder itemOrder) throws ServiceException {
        if(itemOrder.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("itemOrder already exists")
                    .build();
        }
        return  itemOrderRepository.save(itemOrder);
    }

    @Override
    public void delete(ItemOrder itemOrder) throws ServiceException {
        if(itemOrder.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("itemOrder is null")
                    .build();
        }
        itemOrder = findById(itemOrder.getId());
        itemOrder.setDeletedAt(new Date());
        itemOrderRepository.save(itemOrder);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        ItemOrder itemOrder = findById(id);
        itemOrder.setDeletedAt(new Date());
        itemOrderRepository.save(itemOrder);
    }
}
