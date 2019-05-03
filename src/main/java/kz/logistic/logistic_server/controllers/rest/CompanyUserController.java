package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.CompanyUserDto;
import kz.logistic.logistic_server.models.entities.CompanyUser;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.models.mappers.CompanyUserMapper;
import kz.logistic.logistic_server.services.CompanyService;
import kz.logistic.logistic_server.services.CompanyUserService;
import kz.logistic.logistic_server.services.UserService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/companies/users")
public class CompanyUserController extends BaseController {

    private CompanyUserService companyUserService;
    private CompanyUserMapper companyUserMapper;
    private CompanyService companyService;
    private UserService userService;

    @Autowired
    public CompanyUserController(CompanyUserService companyUserService,
                                 CompanyUserMapper companyUserMapper,
                                 CompanyService companyService,
                                 UserService userService) {
        this.companyUserService = companyUserService;
        this.companyUserMapper = companyUserMapper;
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> index(Authentication authentication) throws ServiceException{
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        if (user.isAdmin()) {
            return buildResponse(companyUserMapper.toDtoList(companyUserService.findAll()), HttpStatus.OK);
        } else {
            return buildResponse(companyUserMapper.toDtoList(companyUserService.findAllByCompany(companyService.getCompanyByUser(user))), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CompanyUserDto companyUserDto) throws ServiceException {
        CompanyUser companyUser = companyUserMapper.toEntity(companyUserDto);
        if (companyUserService.findByCompanyAndUser(companyUser.getCompany(), companyUser.getUser()) != null) {
            throw ServiceException.builder().message("user already exists").errorCode(ErrorCode.ALREADY_EXISTS).build();
        }
        companyUserService.save(companyUser);
        return buildResponse(companyUserMapper.toDto(companyUser), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        companyUserService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CompanyUserDto companyUserDto) throws ServiceException {
        companyUserService.delete(companyUserMapper.toEntity(companyUserDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody CompanyUserDto companyUserDto) throws ServiceException {
        CompanyUser companyUser = companyUserService.update(companyUserMapper.toEntity(companyUserDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(companyUserMapper.toDto(companyUser))
                .build(), HttpStatus.OK);
    }


}
