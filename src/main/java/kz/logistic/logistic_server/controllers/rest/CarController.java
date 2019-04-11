package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.CarDto;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.models.mappers.CarMapper;
import kz.logistic.logistic_server.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@RestController
@RequestMapping("/api/cars")
public class CarController extends BaseController{

     private CarService carService;
     private CarMapper carMapper;

     @Autowired
     public CarController(CarService carService, CarMapper carMapper) {
          this.carService = carService;
          this.carMapper = carMapper;
     }

     @GetMapping
     public ResponseEntity<?> index(){
          return buildResponse(carMapper.toDtoList(carService.findAll()), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody CarDto carDto) throws ServiceException{
          Car car = carService.save(carMapper.toEntity(carDto));
          return buildResponse(carMapper.toDto(car),HttpStatus.OK);
     }



}
