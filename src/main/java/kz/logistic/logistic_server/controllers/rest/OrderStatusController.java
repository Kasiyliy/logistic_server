package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.OrderStatusDto;
import kz.logistic.logistic_server.models.entities.OrderStatus;
import kz.logistic.logistic_server.models.mappers.OrderStatusMapper;
import kz.logistic.logistic_server.services.OrderStatusService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/statuses")
public class OrderStatusController extends BaseController{

     private OrderStatusService orderStatusService;
     private OrderStatusMapper orderStatusMapper;

     @Autowired
     public OrderStatusController(OrderStatusService orderStatusService, OrderStatusMapper orderStatusMapper) {
          this.orderStatusService = orderStatusService;
          this.orderStatusMapper = orderStatusMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(orderStatusMapper.toDtoList(orderStatusService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(orderStatusMapper.toDto(orderStatusService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody OrderStatusDto orderStatusDto) throws ServiceException{
          OrderStatus orderStatus = orderStatusService.save(orderStatusMapper.toEntity(orderStatusDto));
          return buildResponse(orderStatusMapper.toDto(orderStatus),HttpStatus.OK);
     }

     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          orderStatusService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody OrderStatusDto orderStatusDto) throws ServiceException{
          orderStatusService.delete(orderStatusMapper.toEntity(orderStatusDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody OrderStatusDto orderStatusDto) throws ServiceException{
          OrderStatus orderStatus = orderStatusService.update(orderStatusMapper.toEntity(orderStatusDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(orderStatusMapper.toDto(orderStatus))
                  .build(), HttpStatus.OK);
     }


}
