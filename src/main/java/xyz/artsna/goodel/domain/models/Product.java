package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Product {

    private String name;
    private BigDecimal price;
    private String description;
    private String category;
    private String imageUrl;


}
