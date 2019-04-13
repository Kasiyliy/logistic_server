package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Order;
import kz.logistic.logistic_server.models.entities.OrderLog;
import kz.logistic.logistic_server.repositories.OrderLogRepository;
import kz.logistic.logistic_server.repositories.OrderRepository;
import kz.logistic.logistic_server.services.OrderLogService;
import kz.logistic.logistic_server.services.OrderService;
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
public class OrderLogServiceImpl implements OrderLogService {

    private OrderLogRepository orderLogRepository;

    @Autowired
    public OrderLogServiceImpl(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    @Override
    public OrderLog findById(Long id) throws ServiceException {
        Optional<OrderLog> orderOptional = orderLogRepository.findById(id);
        return orderOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("orderLog not found")
                .build());
    }

    @Override
    public List<OrderLog> findAll() {
        return orderLogRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<OrderLog> findAllWithDeleted() {
        return orderLogRepository.findAll();
    }

    @Override
    public OrderLog update(OrderLog orderLog) throws ServiceException {
        if(orderLog.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("orderLog is null")
                    .build();
        }
        return  orderLogRepository.save(orderLog);
    }

    @Override
    public OrderLog save(OrderLog orderLog) throws ServiceException {
        if(orderLog.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("orderLog already exists")
                    .build();
        }
        return  orderLogRepository.save(orderLog);
    }

    @Override
    public void delete(OrderLog orderLog) throws ServiceException {
        if(orderLog.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("orderLog is null")
                    .build();
        }
        orderLog = findById(orderLog.getId());
        orderLog.setDeletedAt(new Date());
        orderLogRepository.save(orderLog);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        OrderLog orderLog = findById(id);
        orderLog.setDeletedAt(new Date());
        orderLogRepository.save(orderLog);
    }
}