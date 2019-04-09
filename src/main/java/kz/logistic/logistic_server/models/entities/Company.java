package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends AuditModel{

    @Column(name = "bin")
    @NotNull(message = "bin is required")
    private String bin;

    @Column(unique = true, name = "phone_number")
    @NotNull(message = "phone_number is required")
    private String phoneNumber;

    @Column(name="name")
    @NotNull(message = "name is required")
    private String name;

    @OneToOne
    private User user;
}
