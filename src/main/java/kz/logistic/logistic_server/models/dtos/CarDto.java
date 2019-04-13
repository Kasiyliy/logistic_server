package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import kz.logistic.logistic_server.models.entities.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarDto extends BaseDto {

    private String plateNumber;
    private String name;
    private UserDto user;
    private CarTypeDto carType;
    private CompanyDto company;

}
