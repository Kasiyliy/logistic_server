package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_logs")
public class OrderLog extends AuditModel{

    @OneToOne
    private Order order;

    @Column
    @NotNull(message = "longitude is required")
    private String longitude;

    @Column
    @NotNull(message = "latitude is required")
    private String latitude;
}
