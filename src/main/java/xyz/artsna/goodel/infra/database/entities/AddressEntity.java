package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false) private String city;
    @Column(nullable = false) private String state;
    @Column(nullable = false, name = "zip_code") private String zipCode;
    @Column(nullable = false) private String country;
    @Column(columnDefinition = "TEXT") private String reference;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private ClientEntity client;

    public AddressEntity(ClientEntity client, String name, String street, String city, String state, String zipCode, String country, String reference) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.reference = reference;
        this.client = client;
    }
}