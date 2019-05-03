package kz.logistic.logistic_server.models.entities;

import kz.logistic.logistic_server.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies_users")
public class CompanyUser extends AuditModel{

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @NotNull(message = "company_id is required")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "user_id is required")
    private User user;

}
