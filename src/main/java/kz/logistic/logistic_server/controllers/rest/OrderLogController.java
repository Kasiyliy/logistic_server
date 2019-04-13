package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.OrderLogDto;
import kz.logistic.logistic_server.models.entities.OrderLog;
import kz.logistic.logistic_server.models.mappers.OrderLogMapper;
import kz.logistic.logistic_server.services.OrderLogService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/logs")
public class OrderLogController extends BaseController{

     private OrderLogService orderLogService;
     private OrderLogMapper orderLogMapper;

     @Autowired
     public OrderLogController(OrderLogService orderLogService, OrderLogMapper orderLogMapper) {
          this.orderLogService = orderLogService;
          this.orderLogMapper = orderLogMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(orderLogMapper.toDtoList(orderLogService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(orderLogMapper.toDto(orderLogService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody OrderLogDto orderLogDto) throws ServiceException{
          OrderLog orderLog = orderLogService.save(orderLogMapper.toEntity(orderLogDto));
          return buildResponse(orderLogMapper.toDto(orderLog),HttpStatus.OK);
     }

     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          orderLogService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody OrderLogDto orderLogDto) throws ServiceException{
          orderLogService.delete(orderLogMapper.toEntity(orderLogDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody OrderLogDto orderLogDto) throws ServiceException{
          OrderLog orderLog = orderLogService.update(orderLogMapper.toEntity(orderLogDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(orderLogMapper.toDto(orderLog))
                  .build(), HttpStatus.OK);
     }


}
