package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-neighborhoods")
@Data
@NoArgsConstructor
public class NeighborhoodEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @Column(nullable = false) private String name;

    @Column(nullable = false) private BigDecimal deliveryFee;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private StoreEntity store;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="neighborhood", fetch = FetchType.LAZY)
    private Set<AddressEntity> addresses = Sets.of();

    public NeighborhoodEntity(StoreEntity store, String name, BigDecimal deliveryFee) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.store = store;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NeighborhoodEntity that = (NeighborhoodEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}