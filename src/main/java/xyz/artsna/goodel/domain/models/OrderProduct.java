package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.OrderProductEntity;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class OrderProduct {

    private Order order;

    private Product product;
    private List<Complement> complements;

    private double totalPrice;

    public OrderProduct(OrderProductEntity entity) {
        this.order = new Order(entity.getOrder());
        this.product = new Product(entity.getProduct());
        this.complements = entity.getComplements().stream().map(Complement::new).toList();
        this.totalPrice = entity.getTotalPrice();
    }
}