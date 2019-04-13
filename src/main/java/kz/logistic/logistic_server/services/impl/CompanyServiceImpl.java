package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.repositories.CompanyRepository;
import kz.logistic.logistic_server.repositories.UserRepository;
import kz.logistic.logistic_server.services.CompanyService;
import kz.logistic.logistic_server.services.UserService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findById(Long id) throws ServiceException {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("user not found")
                .build());
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Company> findAllWithDeleted() {
        return companyRepository.findAll();
    }

    @Override
    public Company update(Company company) throws ServiceException {
        if(company.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("company is null")
                    .build();
        }
        return  companyRepository.save(company);
    }

    @Override
    public Company save(Company company) throws ServiceException {
        if(company.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("company already exists")
                    .build();
        }
        return  companyRepository.save(company);
    }

    @Override
    public void delete(Company company) throws ServiceException {
        if(company.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("company is null")
                    .build();
        }
        company = findById(company.getId());
        company.setDeletedAt(new Date());
        companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Company company = findById(id);
        company.setDeletedAt(new Date());
        companyRepository.save(company);
    }
}
