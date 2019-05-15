package kz.logistic.logistic_server.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.CarDto;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.Role;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.models.mappers.CarMapper;
import kz.logistic.logistic_server.services.CarService;
import kz.logistic.logistic_server.services.CompanyService;
import kz.logistic.logistic_server.services.UserService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/cars")
@Api(description = "cars")
public class CarController extends BaseController {

    private CarService carService;
    private CompanyService companyService;
    private UserService userService;
    private CarMapper carMapper;

    @Autowired
    public CarController(CompanyService companyService,
                         UserService userService,
                         CarService carService,
                         CarMapper carMapper) {
        this.companyService = companyService;
        this.userService = userService;
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping
    @ApiOperation("get all by user role")
    public ResponseEntity<?> index(Authentication
                                           authentication) throws ServiceException {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        if(!user.getRole().getName().equals(Role.ROLE_ADMIN_NAME)){
            Company company = companyService.getCompanyByUser(user);
            if(company!=null){
                return buildResponse(carMapper.toDtoList(carService.findCarsByCompany(company.getId())), HttpStatus.OK);
            }else{
                return buildResponse(new ArrayList(), HttpStatus.OK);
            }

        }
        return buildResponse(carMapper.toDtoList(carService.findAll()), HttpStatus.OK);
    }
    @GetMapping("/drivers/{uId}")
    public ResponseEntity<?> index(@PathVariable Long uId) throws ServiceException {
        return buildResponse(carMapper.toDtoList(carService.findCarsByUser(uId)), HttpStatus.OK);
    }

    @GetMapping("/companies/{cId}")
    public ResponseEntity<?> carsByCompany(@PathVariable Long cId) throws ServiceException {
        return buildResponse(carMapper.toDtoList(carService.findCarsByCompany(cId)), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("get by id")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
        return buildResponse(carMapper.toDto(carService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    @ApiOperation("get by user id")
    public ResponseEntity<?> getCarsByUser(@PathVariable Long userId) throws ServiceException{
        return buildResponse(carMapper.toDto(carService.findById(userId)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("add car")
    public ResponseEntity<?> add(@RequestBody CarDto carDto) throws ServiceException {
        Car car = carService.save(carMapper.toEntity(carDto));
        return buildResponse(carMapper.toDto(car), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @ApiOperation("delete by id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        carService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("delete by entity")
    public ResponseEntity<?> delete(@RequestBody CarDto carDto) throws ServiceException {
        carService.delete(carMapper.toEntity(carDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ApiOperation("update by entity")
    public ResponseEntity<?> update(@RequestBody CarDto carDto) throws ServiceException {
        Car car = carService.update(carMapper.toEntity(carDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(carMapper.toDto(car))
                .build(), HttpStatus.OK);
    }


}
