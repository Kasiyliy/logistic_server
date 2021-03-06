package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.UserDto;
import kz.logistic.logistic_server.models.entities.Role;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.models.mappers.UserMapper;
import kz.logistic.logistic_server.services.UserService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {


    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> getAllDrivers() {
        return buildResponse(userMapper.toDtoList(userService.findAllDrivers()), HttpStatus.OK);
    }

    @GetMapping("/drivers/{companyId}")
    public ResponseEntity<?> getAllDrivers(@PathVariable Long companyId) {
        return buildResponse(userMapper.toDtoList(userService.findAllDriversByCompany(companyId)), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() {
        return buildResponse(userMapper.toDtoList(userService.findAllClients()), HttpStatus.OK);
    }

    @GetMapping("/managers")
    public ResponseEntity<?> getAllManagers() {
        return buildResponse(userMapper.toDtoList(userService.findAllManagers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDto userDto) throws ServiceException {
        User user = userMapper.toEntity(userDto);
        Role role = new Role();
        role.setId(Role.ROLE_CLIENT_ID);
        user.setRole(role);
        user = userService.save(user);
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/add/driver")
    public ResponseEntity<?> addDriver(@RequestBody UserDto userDto) throws ServiceException {
        User user = userMapper.toEntity(userDto);
        Role role = new Role();
        role.setId(Role.ROLE_DRIVER_ID);
        user.setRole(role);
        user = userService.save(user);
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String login) throws ServiceException {
        User user = userService.findByLogin(login);
        if (user != null) {
            throw ServiceException.builder().message("Login exists").errorCode(ErrorCode.ALREADY_EXISTS).build();
        } else {
            return buildResponse(SuccessResponse.builder().message("OK").build(), HttpStatus.OK);
        }

    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        userService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws ServiceException {
        userService.delete(userMapper.toEntity(userDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(path = "{id}" , method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Long id) throws ServiceException {
        if(userDto.getId().equals(id)){
            User user = userService.update(userMapper.toEntity(userDto));
            return buildResponse(SuccessResponse.builder()
                    .message("updated")
                    .data(userMapper.toDto(user))
                    .build(), HttpStatus.OK);
        }else{
            return buildResponse(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) throws ServiceException {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        return buildResponse(SuccessResponse.builder()
                .message("found")
                .data(userMapper.toDto(user))
                .build(), HttpStatus.OK);
    }

}
