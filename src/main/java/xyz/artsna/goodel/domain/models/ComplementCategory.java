package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.ComplementCategoryEntity;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ComplementCategory {

    private UUID id;

    private String name;
    private String description;

    private int minimumSelections; //If 0, you will not need to select any complement when creating the order. If > 1, you will be required to select any complement when creating the order.
    private int maximumSelections;

    private List<Complement> complements;

    private Product product;

    public ComplementCategory(ComplementCategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.minimumSelections = entity.getMinimumSelections();
        this.maximumSelections = entity.getMaximumSelections();
        this.complements = entity.getComplements().stream().map(Complement::new).toList();
        this.product = new Product(entity.getProduct());
    }
}
