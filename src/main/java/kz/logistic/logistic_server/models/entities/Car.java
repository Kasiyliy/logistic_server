package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
@ToString
public class Car extends AuditModel {

    @Column(unique = true,name = "plate_number")
    @NotNull(message = "plate_number is required")
    private String plateNumber;

    @ManyToOne
    @JoinColumn(name = "car_type_id" , referencedColumnName = "id")
    @NotNull(message = "car_type_id is required")
    private CarType carType;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id" , referencedColumnName = "id")
    private Company company;

}
