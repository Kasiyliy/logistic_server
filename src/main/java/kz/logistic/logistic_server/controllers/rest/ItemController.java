package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.dtos.ItemDto;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.Item;
import kz.logistic.logistic_server.models.mappers.ItemMapper;
import kz.logistic.logistic_server.services.CompanyService;
import kz.logistic.logistic_server.services.ItemService;
import kz.logistic.logistic_server.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/items")
public class ItemController extends BaseController {

    private CompanyService companyService;
    private ItemService itemService;
    private ItemMapper itemMapper;

    @Autowired
    public ItemController(CompanyService companyService,
                          ItemService itemService,
                          ItemMapper itemMapper) {
        this.companyService = companyService;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping
    public ResponseEntity<?> index() {
        return buildResponse(itemMapper.toDtoList(itemService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<?> getByCompany(@PathVariable Long companyId) throws ServiceException{
        Company company = companyService.findById(companyId);
        return buildResponse(itemMapper.toDtoList(itemService.findAllByCompany(company)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ItemDto itemDto) throws ServiceException {
        Item item = itemService.save(itemMapper.toEntity(itemDto));
        return buildResponse(itemMapper.toDto(item), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        itemService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody ItemDto itemDto) throws ServiceException {
        itemService.delete(itemMapper.toEntity(itemDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody ItemDto itemDto) throws ServiceException {
        Item item = itemService.update(itemMapper.toEntity(itemDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(itemMapper.toDto(item))
                .build(), HttpStatus.OK);
    }


}
