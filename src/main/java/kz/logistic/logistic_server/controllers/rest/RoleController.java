package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.RoleDto;
import kz.logistic.logistic_server.models.entities.Role;
import kz.logistic.logistic_server.models.mappers.RoleMapper;
import kz.logistic.logistic_server.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController{

     private RoleService roleService;
     private RoleMapper roleMapper;

     @Autowired
     public RoleController(RoleService roleService, RoleMapper roleMapper) {
          this.roleService = roleService;
          this.roleMapper = roleMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll(){
          return buildResponse(roleMapper.toDtoList(roleService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException{
          return buildResponse(roleMapper.toDto(roleService.findById(id)), HttpStatus.OK);
     }

}
