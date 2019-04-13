package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.CarTypeDto;
import kz.logistic.logistic_server.models.entities.CarType;
import kz.logistic.logistic_server.models.mappers.CarTypeMapper;
import kz.logistic.logistic_server.services.CarTypeService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car/types")
public class CarTypeController extends BaseController{

     private CarTypeMapper carTypeMapper;
     private CarTypeService carTypeService;

     @Autowired
     public CarTypeController(CarTypeMapper carTypeMapper, CarTypeService carTypeService) {
          this.carTypeMapper = carTypeMapper;
          this.carTypeService = carTypeService;
     }

     @GetMapping
     public ResponseEntity<?> getAll() throws ServiceException{
          return buildResponse(carTypeMapper.toDtoList(carTypeService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(carTypeMapper.toDto(carTypeService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody CarTypeDto carTypeDto) throws ServiceException{
          CarType carType = carTypeService.save(carTypeMapper.toEntity(carTypeDto));
          return buildResponse(carTypeMapper.toDto(carType),HttpStatus.OK);
     }

     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          carTypeService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody CarTypeDto carTypeDto) throws ServiceException{
          carTypeService.delete(carTypeMapper.toEntity(carTypeDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody CarTypeDto carTypeDto) throws ServiceException{
          CarType carType = carTypeService.update(carTypeMapper.toEntity(carTypeDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(carTypeMapper.toDto(carType))
                  .build(), HttpStatus.OK);
     }



}
