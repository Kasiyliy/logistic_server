package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.CompanyDto;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper extends AbstractModelMapper<Company, CompanyDto> {

    private ModelMapper modelMapper;
    private UserMapper userMapper;

    @Autowired
    public CompanyMapper(ModelMapper modelMapper,
                         UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }

    @Override
    public CompanyDto toDto(Company company) {
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        if (company.getUser() != null) {
            companyDto.setUser(userMapper.toDto(company.getUser()));
        }
        return companyDto;
    }

    @Override
    public Company toEntity(CompanyDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        if (companyDto.getUser() != null) {
            company.setUser(userMapper.toEntity(companyDto.getUser()));
        }
        return company;
    }

    @Override
    public List<CompanyDto> toDtoList(List<Company> companys) {
        return companys.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Company> toEntityList(List<CompanyDto> companyDtos) {
        return companyDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
