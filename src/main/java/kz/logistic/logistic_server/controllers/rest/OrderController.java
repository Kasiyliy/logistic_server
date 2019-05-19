package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.OrderDto;
import kz.logistic.logistic_server.models.entities.Order;
import kz.logistic.logistic_server.models.entities.OrderStatus;
import kz.logistic.logistic_server.models.mappers.OrderMapper;
import kz.logistic.logistic_server.services.OrderService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController{

     private OrderService orderService;
     private OrderMapper orderMapper;

     @Autowired
     public OrderController(OrderService orderService, OrderMapper orderMapper) {
          this.orderService = orderService;
          this.orderMapper = orderMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(orderMapper.toDtoList(orderService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}/company")
     public ResponseEntity<?> getAllByCompanyId(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByCompanyId(id)), HttpStatus.OK);
     }

     @GetMapping("{id}/driver")
     public ResponseEntity<?> getAllByDriverId(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByDriverId(id)), HttpStatus.OK);
     }

     @GetMapping("{id}/client")
     public ResponseEntity<?> getAllByClientId(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByClientId(id)), HttpStatus.OK);
     }

     @GetMapping("{id}/history/company")
     public ResponseEntity<?> getAllByCompanyIdHistory(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByCompanyIdHistory(id)), HttpStatus.OK);
     }

     @GetMapping("{id}/history/driver")
     public ResponseEntity<?> getAllByDriverIdHistory(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByDriverIdHistory(id)), HttpStatus.OK);
     }

     @GetMapping("{id}/history/client")
     public ResponseEntity<?> getAllByClientIdHistory(@PathVariable Long id){
          return buildResponse(orderMapper.toDtoList(orderService.findAllWithDeletedByClientIdHistory(id)), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(orderMapper.toDto(orderService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody OrderDto orderDto) throws ServiceException{
          Order order = orderMapper.toEntity(orderDto);
          if(order.isCompletedByClient() && order.isCompletedByDriver()){
               OrderStatus status = new OrderStatus();
               status.setId(OrderStatus.RECEIVED_ID);
               status.setName(OrderStatus.RECEIVED);
               order.setStatus(status);
          }
          order = orderService.save(order);
          return buildResponse(orderMapper.toDto(order),HttpStatus.OK);
     }

     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          orderService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody OrderDto orderDto) throws ServiceException{
          orderService.delete(orderMapper.toEntity(orderDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(path = "{id}",method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody OrderDto orderDto, @PathVariable Long id) throws ServiceException{
          if(!orderDto.getId().equals(id)){
               return buildResponse(HttpStatus.NOT_FOUND);
          }

          Order order = orderMapper.toEntity(orderDto);
          if(order.isCompletedByClient() && order.isCompletedByDriver()){
               OrderStatus status = new OrderStatus();
               status.setId(OrderStatus.RECEIVED_ID);
               status.setName(OrderStatus.RECEIVED);
               order.setStatus(status);
          }
          order = orderService.update(order);
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(orderMapper.toDto(order))
                  .build(), HttpStatus.OK);
     }


}
