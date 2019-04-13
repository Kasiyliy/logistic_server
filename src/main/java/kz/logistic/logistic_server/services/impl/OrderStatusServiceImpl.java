package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.OrderStatus;
import kz.logistic.logistic_server.repositories.OrderStatusRepository;
import kz.logistic.logistic_server.services.OrderStatusService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository OrderStatusRepository) {
        this.orderStatusRepository = OrderStatusRepository;
    }

    @Override
    public OrderStatus findById(Long id) throws ServiceException {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(id);
        return orderStatusOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("orderStatus not found")
                .build());
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<OrderStatus> findAllWithDeleted() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus update(OrderStatus orderStatus) throws ServiceException {
        if(orderStatus.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("orderStatus is null")
                    .build();
        }
        return  orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus save(OrderStatus orderStatus) throws ServiceException {
        if(orderStatus.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("orderStatus already exists")
                    .build();
        }
        return  orderStatusRepository.save(orderStatus);
    }

    @Override
    public void delete(OrderStatus orderStatus) throws ServiceException {
        if(orderStatus.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("orderStatus is null")
                    .build();
        }
        orderStatus = findById(orderStatus.getId());
        orderStatus.setDeletedAt(new Date());
        orderStatusRepository.save(orderStatus);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        OrderStatus orderStatus = findById(id);
        orderStatus.setDeletedAt(new Date());
        orderStatusRepository.save(orderStatus);
    }
}