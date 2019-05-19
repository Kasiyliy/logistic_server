package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Order;
import kz.logistic.logistic_server.repositories.OrderRepository;
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
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository OrderRepository) {
        this.orderRepository = OrderRepository;
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("order not found")
                .build());
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Order> findAllWithDeleted() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) throws ServiceException {
        if(order.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("order is null")
                    .build();
        }
        return  orderRepository.save(order);
    }

    @Override
    public Order save(Order order) throws ServiceException {
        if(order.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("order already exists")
                    .build();
        }
        return  orderRepository.save(order);
    }

    @Override
    public void delete(Order order) throws ServiceException {
        if(order.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("order is null")
                    .build();
        }
        order = findById(order.getId());
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Order order = findById(id);
        order.setDeletedAt(new Date());
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAllWithDeletedByCompanyId(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndCompanyId(id);
    }

    @Override
    public List<Order> findAllWithDeletedByClientId(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndClientId(id);
    }

    @Override
    public List<Order> findAllWithDeletedByDriverId(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndDriverId(id);
    }

    @Override
    public List<Order> findAllWithDeletedByCompanyIdHistory(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndCompanyIdHistory(id);
    }

    @Override
    public List<Order> findAllWithDeletedByClientIdHistory(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndClientIdHistory(id);
    }

    @Override
    public List<Order> findAllWithDeletedByDriverIdHistory(Long id) {
        return orderRepository.findAllByDeletedAtIsNullAndDriverIdHistory(id);
    }
}