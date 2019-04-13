package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto extends BaseDto {
    private int price;
    private UserDto driver;
    private UserDto client;
    private String description;
    private boolean completedByClient;
    private boolean completedByDriver;
    private String fromLocation;
    private String toLocation;

}
