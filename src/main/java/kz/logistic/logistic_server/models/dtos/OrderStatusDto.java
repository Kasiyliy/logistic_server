package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Data
@NoArgsConstructor
public class OrderStatusDto extends BaseDto{

    private String name;

}
