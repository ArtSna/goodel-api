package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-product-complements")
@Data
@NoArgsConstructor
public class ProductComplementEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String name;
    private BigDecimal price;
    private String description;

    private Integer minAmount, maxAmount;
    private Boolean required;

    //finish
    private Set<ProductComplementOptionEntity> options = Sets.of();

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false, updatable=false)
    private ProductEntity product;

    public ProductComplementEntity(ProductEntity product, String name, BigDecimal price, String description, Integer minAmount, Integer maxAmount, Boolean required) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.description = description;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.required = required;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductComplementEntity)) return false;
        ProductComplementEntity other = (ProductComplementEntity) obj;
        return id.equals(other.id);
    }
}