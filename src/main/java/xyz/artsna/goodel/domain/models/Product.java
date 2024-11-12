package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.artsna.goodel.infra.database.entities.ProductEntity;

@Data
@AllArgsConstructor
public class Product {

    public Product(ProductEntity entity) {

    }

}
