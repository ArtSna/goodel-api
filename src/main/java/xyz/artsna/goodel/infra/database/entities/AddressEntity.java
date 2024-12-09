package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "goodel-addresses")
@Data
@NoArgsConstructor
public class AddressEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @Column(nullable = false) private String name;

    @Column(nullable = false) private String street;
    @Column(nullable = false) private Integer number;
    @Column(nullable = false) private String city;
    @Column(nullable = false) private String state;
    @Column(nullable = false, name = "zip_code") private String zipCode;
    @Column(nullable = false) private String country;
    @Column(columnDefinition = "TEXT") private String complement;
    @Column(columnDefinition = "TEXT") private String reference;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private NeighborhoodEntity neighborhood;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private ClientEntity client;

    public AddressEntity(ClientEntity client, NeighborhoodEntity neighborhood, String name, String street, Integer number, String city, String state, String zipCode, String country, String reference, String complement) {
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

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AddressEntity that = (AddressEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}