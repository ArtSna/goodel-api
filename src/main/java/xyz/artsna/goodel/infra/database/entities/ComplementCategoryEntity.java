package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel_complement_categories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ComplementCategoryEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false) private String name;
    private String description;

    @Column(name = "minimum_selections", nullable = false) private int minimumSelections; //If 0, you will not need to select any complement when creating the order. If > 1, you will be required to select any complement when creating the order.
    @Column(name = "maximum_selections", nullable = false) private int maximumSelections;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store")
    private Set<ComplementEntity> complements;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private ProductEntity product;

    public ComplementCategoryEntity(ProductEntity product, String name, String description, int minimumSelections, int maximumSelections) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.name = name;
        this.description = description;
        this.minimumSelections = minimumSelections;
        this.maximumSelections = maximumSelections;
    }

}