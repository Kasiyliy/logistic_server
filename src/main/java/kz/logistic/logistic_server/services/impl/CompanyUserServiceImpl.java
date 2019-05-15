package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.CompanyUser;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.repositories.CompanyUserRepository;
import kz.logistic.logistic_server.services.CompanyUserService;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    private CompanyUserRepository companyUserRepository;

    @Autowired
    public CompanyUserServiceImpl(CompanyUserRepository companyUserRepository) {
        this.companyUserRepository = companyUserRepository;
    }

    @Override
    public CompanyUser findById(Long id) throws ServiceException {
        Optional<CompanyUser> companyUserOptional = companyUserRepository.findById(id);
        return companyUserOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("companyUser not found")
                .build());
    }

    @Override
    public List<CompanyUser> findAll() {
        return companyUserRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<CompanyUser> findAllWithDeleted() {
        return companyUserRepository.findAll();
    }

    @Override
    public CompanyUser update(CompanyUser companyUser) throws ServiceException {
        if(companyUser.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("companyUser is null")
                    .build();
        }
        return  companyUserRepository.save(companyUser);
    }

    @Override
    public CompanyUser save(CompanyUser companyUser) throws ServiceException {
        if(companyUser.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("companyUser already exists")
                    .build();
        }
        return  companyUserRepository.save(companyUser);
    }

    @Override
    public void delete(CompanyUser companyUser) throws ServiceException {
        if(companyUser.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("companyUser is null")
                    .build();
        }
        companyUser = findById(companyUser.getId());
        companyUser.setDeletedAt(new Date());
        companyUserRepository.save(companyUser);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        CompanyUser companyUser = findById(id);
        companyUser.setDeletedAt(new Date());
        companyUserRepository.save(companyUser);
    }

    @Override
    public List<CompanyUser> findAllByCompany(Company company) {
        return companyUserRepository.findAllByCompanyAndDeletedAtIsNull(company);
    }

    @Override
    public CompanyUser findByCompanyAndUser(Company company, User user) {
        return companyUserRepository.findByCompanyAndUserAndDeletedAtIsNull(company, user);
    }

    @Override
    public List<CompanyUser> findAllCompanyUserByUser(Long userId) {
        return companyUserRepository.findAllByUserIdAndDeletedAtIsNull(userId);
    }
}
