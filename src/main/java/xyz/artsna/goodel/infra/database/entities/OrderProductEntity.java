package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import xyz.artsna.goodel.infra.database.keys.OrderProductEntityKey;

import java.util.Set;

@Entity
@Table(name = "goodel_order_products")
public class OrderProductEntity extends PanacheEntityBase {

    @EmbeddedId private OrderProductEntityKey key;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "order_product", cascade = CascadeType.ALL)
    private Set<ComplementEntity> complements;

    @Column(nullable = false, name = "total_price") private double totalPrice;

    public OrderProductEntity(OrderEntity order, ProductEntity product, Set<ComplementEntity> complements){
        this.key = new OrderProductEntityKey(order, product);
        this.order = order;
        this.product = product;
        this.complements = complements;
        this.totalPrice = product.getPrice();

        if(complements != null && !complements.isEmpty()) {
            complements.forEach(c -> this.totalPrice += c.getPrice());
        }
    }
}
