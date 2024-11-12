package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.ComplementEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Complement {

    private UUID id;

    private String name;
    private String description;
    private String image;
    private double price;

    private boolean active;

    private ComplementCategory category;

    private Product product;

    public Complement(ComplementEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.image = entity.getImage();
        this.price = entity.getPrice();
        this.active = entity.isActive();
        this.category = new ComplementCategory(entity.getCategory());
        this.product = new Product(entity.getProduct());
    }
}