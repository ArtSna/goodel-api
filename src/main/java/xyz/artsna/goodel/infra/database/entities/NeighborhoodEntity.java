package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "goodel_neighborhoods")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NeighborhoodEntity extends PanacheEntityBase {

    @Id
    private UUID id;

    @Column(nullable = false) private String name;

    @Column(nullable = false) private BigDecimal deliveryFee;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private StoreEntity store;

    public NeighborhoodEntity(StoreEntity store, String name, BigDecimal deliveryFee) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.store = store;
    }
}