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
@Table(name = "orders")
public class Order extends AuditModel{

    @Column(name = "price")
    @NotNull(message = "price is required")
    private int price;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @Column
    @NotNull(message = "description is required")
    private String description;

    @Column(name = "completed_by_client")
    private boolean completedByClient = false;

    @Column(name = "completed_by_driver")
    private boolean completedByDriver = false;

    @Column(name = "from_location")
    @NotNull(message = "from_location is required")
    private String fromLocation;

    @Column(name = "to_location")
    @NotNull(message = "to_location is required")
    private String toLocation;
}
