package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_statuses")
public class OrderStatus extends AuditModel {

    public static String  RECEIVED = "RECEIVED";
    public static String  CANCELED = "CANCELED";
    public static String  IN_PROCESS = "IN_PROCESS";
    public static String  UNDER_CONSIDERATION = "UNDER_CONSIDERATION";

    public static Long RECEIVED_ID = 1L;
    public static Long CANCELED_ID = 2L;
    public static Long IN_PROCESS_ID = 3L;
    public static Long UNDER_CONSIDERATION_ID = 4L;

    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

}
