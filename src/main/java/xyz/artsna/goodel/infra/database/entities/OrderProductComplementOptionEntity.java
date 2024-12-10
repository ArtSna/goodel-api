package xyz.artsna.goodel.infra.database.entities;

import jakarta.persistence.EmbeddedId;

import java.math.BigDecimal;

public class OrderProductComplementOptionEntity {

    @EmbeddedId

    private String name;
    private String description;
    private BigDecimal price;

    private OrderProductComplementEntity complement;

}
