package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.NeighborhoodEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Neighborhood {

    private UUID id;
    private String name;
    private BigDecimal deliveryFee;

    public Neighborhood(NeighborhoodEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.deliveryFee = entity.getDeliveryFee();
    }
}