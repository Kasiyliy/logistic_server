package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.audits.AuditModel;
import kz.logistic.logistic_server.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLogDto extends BaseDto{

    private OrderDto order;
    private String longitude;
    private String latitude;

}
