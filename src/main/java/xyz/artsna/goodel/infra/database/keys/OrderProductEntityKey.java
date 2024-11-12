package xyz.artsna.goodel.infra.database.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.OrderEntity;
import xyz.artsna.goodel.infra.database.entities.ProductEntity;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class OrderProductEntityKey implements Serializable {

    @ManyToOne private OrderEntity order;
    @ManyToOne private ProductEntity product;

}
