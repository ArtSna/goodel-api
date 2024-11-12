package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import xyz.artsna.goodel.infra.database.enums.DeliveryMethod;
import xyz.artsna.goodel.infra.database.enums.OrderStatus;
import xyz.artsna.goodel.infra.database.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "goodel_orders")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderEntity extends PanacheEntityBase {

    @Id @GeneratedValue private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderProductEntity> products;

    @Column(name = "payment_method", nullable = false) private PaymentMethod paymentMethod;
    @Column(name = "cash_change") private double cashChange;

    @Column(name = "delivery_method", nullable = false) private DeliveryMethod deliveryMethod;
    @Column(name = "shipping_address") private AddressEntity shippingAddress;

    private OrderStatus status = OrderStatus.PENDING;
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "total_price", nullable = false) private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public OrderEntity(ClientEntity client, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, AddressEntity shippingAddress, double cashChange, String comment, double totalPrice) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.shippingAddress = shippingAddress;
        this.cashChange = cashChange;
        this.comment = comment;
        this.totalPrice = totalPrice;
    }

}