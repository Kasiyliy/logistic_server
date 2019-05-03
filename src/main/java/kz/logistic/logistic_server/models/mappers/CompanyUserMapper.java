package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.CompanyUserDto;
import kz.logistic.logistic_server.models.entities.CompanyUser;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CompanyUserMapper extends AbstractModelMapper<CompanyUser, CompanyUserDto> {

    private ModelMapper modelMapper;
    private CompanyMapper companyMapper;
    private UserMapper userMapper;

    @Autowired
    public CompanyUserMapper(ModelMapper modelMapper,
                             CompanyMapper companyMapper,
                             UserMapper userMapper) {
        this.modelMapper = modelMapper;
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;
    }

    @Override
    public CompanyUserDto toDto(CompanyUser companyUser) {
        CompanyUserDto companyUserDto = modelMapper.map(companyUser, CompanyUserDto.class);
        if (companyUser.getCompany() != null) {
            companyUserDto.setCompany(companyMapper.toDto(companyUser.getCompany()));
        }

        if (companyUser.getUser() != null) {
            companyUserDto.setUser(userMapper.toDto(companyUser.getUser()));
        }
        return companyUserDto;
    }

    @Override
    public CompanyUser toEntity(CompanyUserDto companyUserDto) {
        CompanyUser companyUser = modelMapper.map(companyUserDto, CompanyUser.class);

        if (companyUserDto.getCompany() != null) {
            companyUser.setCompany(companyMapper.toEntity(companyUserDto.getCompany()));
        }

        if (companyUserDto.getUser() != null) {
            companyUser.setUser(userMapper.toEntity(companyUserDto.getUser()));
        }
        return companyUser;
    }

    @Override
    public List<CompanyUserDto> toDtoList(List<CompanyUser> companyUsers) {
        return companyUsers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyUser> toEntityList(List<CompanyUserDto> companyUserDtos) {
        return companyUserDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
