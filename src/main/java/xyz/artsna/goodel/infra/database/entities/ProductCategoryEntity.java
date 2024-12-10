package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-product-categories")
@Data
@NoArgsConstructor
public class ProductCategoryEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String name;
    private String description;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="category", fetch = FetchType.LAZY)
    private Set<ProductEntity> products = Sets.of();

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private StoreEntity store;

    public ProductCategoryEntity(StoreEntity store, String name, String description) {
        this.store = store;
        this.name = name;
        this.description = description;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductCategoryEntity that = (ProductCategoryEntity) obj;
        return id.equals(that.id);
    }
}