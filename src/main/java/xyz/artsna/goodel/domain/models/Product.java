package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private UUID id;

    private String name;
    private String description;
    private String image;
    private double price;
    private boolean active;

    private List<ComplementCategory> complementCategories;

    private ProductCategory category;

    public Store store;

    public Product(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.image = entity.getImage();
        this.price = entity.getPrice();
        this.active = entity.isActive();
        this.complementCategories = entity.getComplementCategories().stream().map(ComplementCategory::new).toList();
        this.category = new ProductCategory(entity.getCategory());
        this.store = new Store(entity.getStore());
    }
}