package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-order-product-complements")
@Data
@NoArgsConstructor
public class OrderProductComplementEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    private Set<OrderProductComplementOptionEntity> options;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false, updatable=false)
    private OrderProductEntity product;

    public OrderProductComplementEntity(OrderProductEntity product, ProductComplementEntity complement, int quantity) {
        this.name = complement.getName();
        this.description = complement.getDescription();
        this.price = complement.getPrice();
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderProductComplementEntity that = (OrderProductComplementEntity) obj;
        return id.equals(that.id);
    }
}