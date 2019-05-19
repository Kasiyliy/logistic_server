package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.ItemOrderDto;
import kz.logistic.logistic_server.models.entities.ItemOrder;
import kz.logistic.logistic_server.models.mappers.ItemOrderMapper;
import kz.logistic.logistic_server.services.ItemOrderService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/items/orders")
public class ItemOrderController extends BaseController{

     private ItemOrderService itemOrderService;
     private ItemOrderMapper itemOrderMapper;

     @Autowired
     public ItemOrderController(ItemOrderService itemOrderService, ItemOrderMapper itemOrderMapper) {
          this.itemOrderService = itemOrderService;
          this.itemOrderMapper = itemOrderMapper;
     }

     @GetMapping
     public ResponseEntity<?> index(){
          return buildResponse(itemOrderMapper.toDtoList(itemOrderService.findAll()), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody ItemOrderDto itemOrderDto) throws ServiceException{
          ItemOrder itemOrder = itemOrderService.save(itemOrderMapper.toEntity(itemOrderDto));
          return buildResponse(itemOrderMapper.toDto(itemOrder),HttpStatus.OK);
     }

     @PostMapping("/all")
     public ResponseEntity<?> addAll(@RequestBody List<ItemOrderDto> itemOrderDtos) throws ServiceException{
          List<ItemOrder> itemOrders = itemOrderService.saveAll(itemOrderMapper.toEntityList(itemOrderDtos));
          return buildResponse(itemOrderMapper.toDtoList(itemOrders),HttpStatus.OK);
     }

     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          itemOrderService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody ItemOrderDto itemOrderDto) throws ServiceException{
          itemOrderService.delete(itemOrderMapper.toEntity(itemOrderDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody ItemOrderDto itemOrderDto) throws ServiceException{
          ItemOrder itemOrder = itemOrderService.update(itemOrderMapper.toEntity(itemOrderDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(itemOrderMapper.toDto(itemOrder))
                  .build(), HttpStatus.OK);
     }



}
