package kz.logistic.logistic_server.models.dtos;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLogDto extends AuditModel{

    private OrderDto orderDto;
    private String longitude;
    private String latitude;

}
