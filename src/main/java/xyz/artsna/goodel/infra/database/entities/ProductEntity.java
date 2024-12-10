package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-products")
@Data
@NoArgsConstructor
public class ProductEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String name;
    private BigDecimal price;
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private ProductCategoryEntity category;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="product", fetch = FetchType.LAZY)
    private Set<ProductComplementEntity> complements;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false, updatable=false)
    private StoreEntity store;

    public ProductEntity(StoreEntity store, ProductCategoryEntity category, String name, BigDecimal price, String description) {
        this.store = store;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.complements = Set.of();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductEntity that = (ProductEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}
