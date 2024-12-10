package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.artsna.goodel.infra.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-orders")
@Data
@NoArgsConstructor
public class OrderEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @OneToMany(cascade= CascadeType.ALL, mappedBy="order", fetch = FetchType.LAZY)
    private Set<OrderProductEntity> products;

    private BigDecimal totalPrice;

    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false, updatable=false)
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false, updatable=false)
    private StoreEntity store;

    public OrderEntity(ClientEntity client, StoreEntity store, BigDecimal totalPrice, Set<OrderProductEntity> products) {
        this.client = client;
        this.store = store;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderEntity that = (OrderEntity) obj;
        return id.equals(that.id);
    }
}
