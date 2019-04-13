package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RoleDto extends BaseDto{

    private String name;

}
