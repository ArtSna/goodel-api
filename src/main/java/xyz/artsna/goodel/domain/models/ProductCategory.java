package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.artsna.goodel.infra.database.entities.ProductCategoryEntity;

@Data
@AllArgsConstructor
public class ProductCategory {

    public ProductCategory(ProductCategoryEntity entity) {

    }

}
