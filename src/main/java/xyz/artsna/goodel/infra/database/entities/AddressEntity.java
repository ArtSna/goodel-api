package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.artsna.goodel.domain.models.Neighborhood;

import java.util.UUID;

@Entity
@Table(name = "goodel_addresses")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AddressEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false) private String name;

    @Column(nullable = false) private String street;
    @Column(nullable = false) private Integer number;
    @Column(nullable = false) private String city;
    @Column(nullable = false) private String state;
    @Column(nullable = false, name = "zip_code") private String zipCode;
    @Column(nullable = false) private String country;
    @Column(nullable = false) private NeighborhoodEntity neighborhood;
    @Column(columnDefinition = "TEXT") private String complement;
    @Column(columnDefinition = "TEXT") private String reference;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private ClientEntity client;

    public AddressEntity(ClientEntity client, NeighborhoodEntity neighborhood, String name, String street, Integer number, String city, String state, String zipCode, String country, String reference, String complement) {
        this.id = UUID.randomUUID();
        this.neighborhood = neighborhood;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.reference = reference;
        this.complement = complement;
        this.client = client;
    }
}