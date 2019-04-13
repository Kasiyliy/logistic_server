package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.UserDto;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.models.mappers.UserMapper;
import kz.logistic.logistic_server.services.UserService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
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
@RequestMapping("/api/users")
public class UserController extends BaseController{

     private UserService userService;
     private UserMapper userMapper;

     @Autowired
     public UserController(UserService userService, UserMapper userMapper) {
          this.userService = userService;
          this.userMapper = userMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody UserDto userDto) throws ServiceException{
          User user = userService.save(userMapper.toEntity(userDto));
          return buildResponse(userMapper.toDto(user),HttpStatus.OK);
     }


     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          userService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws ServiceException{
          userService.delete(userMapper.toEntity(userDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody UserDto userDto) throws ServiceException{
          User user = userService.update(userMapper.toEntity(userDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(userMapper.toDto(user))
                  .build(), HttpStatus.OK);
     }

}
