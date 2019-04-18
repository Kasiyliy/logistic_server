package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item extends AuditModel{

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @NotNull(message = "company_id is required")
    private Company company;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private int price;

    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

}
