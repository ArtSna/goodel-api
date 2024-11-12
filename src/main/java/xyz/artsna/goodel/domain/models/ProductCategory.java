package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.ProductCategoryEntity;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProductCategory {

    private UUID id;

    private String name;
    private String description;

    private List<Product> products;

    public Store store;

    public ProductCategory(ProductCategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.products = entity.getProducts().stream().map(Product::new).toList();
        this.store = new Store(entity.getStore());
    }
}