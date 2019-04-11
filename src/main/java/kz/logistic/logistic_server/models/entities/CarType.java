package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car_types")
@NoArgsConstructor
@Data
public class CarType extends AuditModel {

    @Column(name="name")
    @NotNull(message = "name is required")
    private String name;

}
