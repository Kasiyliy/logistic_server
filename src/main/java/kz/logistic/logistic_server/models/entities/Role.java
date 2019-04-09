package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditModel {

    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_MANAGER_ID = 2L;
    public final static Long ROLE_DRIVER_ID = 3L;
    public final static Long ROLE_CLIENT_ID = 4L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_MANAGER_NAME = "ROLE_MANAGER";
    public final static String ROLE_DRIVER_NAME = "ROLE_DRIVER";
    public final static String ROLE_CLIENT_NAME = "ROLE_CLIENT";

    @Column(unique = true)
    private String name;
}
