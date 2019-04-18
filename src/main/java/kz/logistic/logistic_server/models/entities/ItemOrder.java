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
@Table(name = "items_orders")
public class ItemOrder extends AuditModel{

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @NotNull(message = "item_id is required")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @NotNull(message = "order_id is required")
    private Order order;

}
