package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.CompanyDto;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.mappers.CompanyMapper;
import kz.logistic.logistic_server.services.CompanyService;
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
@RequestMapping("/api/companies")
public class CompanyController extends BaseController{

     private CompanyService companyService;
     private CompanyMapper companyMapper;

     @Autowired
     public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
          this.companyService = companyService;
          this.companyMapper = companyMapper;
     }

     @GetMapping
     public ResponseEntity<?> index(){
          return buildResponse(companyMapper.toDtoList(companyService.findAll()), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody CompanyDto companyDto) throws ServiceException{
          Company company = companyService.save(companyMapper.toEntity(companyDto));
          return buildResponse(companyMapper.toDto(company),HttpStatus.OK);
     }


     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException{
          companyService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody CompanyDto companyDto) throws ServiceException{
          companyService.delete(companyMapper.toEntity(companyDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody CompanyDto companyDto) throws ServiceException{
          Company company = companyService.update(companyMapper.toEntity(companyDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(companyMapper.toDto(company))
                  .build(), HttpStatus.OK);
     }



}
