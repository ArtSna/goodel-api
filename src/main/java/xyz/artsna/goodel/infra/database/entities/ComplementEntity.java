package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "goodel-complements")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ComplementEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false) private String name;
    private String description;
    private String image;

    @Column(nullable = false) private double price;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private ComplementCategoryEntity category;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private ProductEntity product;

    public ComplementEntity(ProductEntity product, ComplementCategoryEntity category, String name, String description, String image, double price) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

}