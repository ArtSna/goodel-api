package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-order-products")
@Data
@NoArgsConstructor
public class OrderProductEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    private String category;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="product", fetch = FetchType.LAZY)
    private Set<OrderProductComplementEntity> complements = Sets.of();

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false, updatable=false)
    private OrderEntity order;

    public OrderProductEntity(OrderEntity order, ProductEntity product, int quantity) {
        this.order = order;
        this.category = product.getCategory().getName();
        this.quantity = quantity;
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderProductEntity that = (OrderProductEntity) obj;
        return id.equals(that.id);
    }
}
