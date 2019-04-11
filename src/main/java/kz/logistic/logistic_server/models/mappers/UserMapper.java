package kz.logistic.logistic_server.models.mappers;

import kz.logistic.logistic_server.models.dtos.UserDto;
import kz.logistic.logistic_server.models.entities.User;
import kz.logistic.logistic_server.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper extends AbstractModelMapper<User,UserDto> {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        return  userDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
