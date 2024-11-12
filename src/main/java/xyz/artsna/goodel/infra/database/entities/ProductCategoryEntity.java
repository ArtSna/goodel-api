package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel_product_categories")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(unique = true, nullable = false) private String name;
    private String description;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="category")
    private Set<ProductEntity> products;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    public StoreEntity store;

    public ProductCategoryEntity(StoreEntity store, String name, String description) {
        this.id = UUID.randomUUID();
        this.store = store;
        this.name = name;
        this.description = description;
    }
}