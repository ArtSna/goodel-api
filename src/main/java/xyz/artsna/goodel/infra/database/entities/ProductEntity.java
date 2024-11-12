package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-products")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false) private String name;
    @Column(columnDefinition = "TEXT") private String description;
    private String image;

    @Column(nullable = false) private double price;

    private boolean active = true;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store")
    private Set<ComplementCategoryEntity> complementCategories;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store")
    private Set<ComplementEntity> complements;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategoryEntity category;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    public StoreEntity store;

    public ProductEntity(StoreEntity store, ProductCategoryEntity category, String name, String description, String image, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.store = store;
    }

}