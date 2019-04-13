package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String login;

    private String password;

    private RoleDto role;

}
