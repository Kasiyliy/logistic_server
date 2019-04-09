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
@Table(name = "cars")
public class Car extends AuditModel {

    @Column(unique = true,name = "plate_number")
    @NotNull(message = "plate_number is required")
    private String plateNumber;

    @NotNull(message = "car_type_id is required")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_type_id" , referencedColumnName = "id")
    private CarType carType;

    @OneToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id" , referencedColumnName = "id")
    private Company company;

}
