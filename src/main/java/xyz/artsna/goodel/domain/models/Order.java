package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.OrderEntity;
import xyz.artsna.goodel.infra.database.enums.DeliveryMethod;
import xyz.artsna.goodel.infra.database.enums.OrderStatus;
import xyz.artsna.goodel.infra.database.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Order {

    private Long id;

    private List<OrderProduct> products;

    private PaymentMethod paymentMethod;
    private double cashChange;

    private DeliveryMethod deliveryMethod;
    private Address shippingAddress;

    private OrderStatus status;
    private String comment;
    private double totalPrice;

    private Client client;

    private LocalDateTime createdAt;

    public Order(OrderEntity entity) {
        this.id = entity.getId();
        this.products = entity.getProducts().stream().map(OrderProduct::new).toList();
        this.paymentMethod = entity.getPaymentMethod();
        this.cashChange = entity.getCashChange();
        this.deliveryMethod = entity.getDeliveryMethod();
        this.shippingAddress = new Address(entity.getShippingAddress());
        this.status = entity.getStatus();
        this.comment = entity.getComment();
        this.totalPrice = entity.getTotalPrice();
        this.client = new Client(entity.getClient());
        this.createdAt = entity.getCreatedAt();  // Assuming 'createdAt' is a JPA generated column (TIMESTAMP)
    }
}