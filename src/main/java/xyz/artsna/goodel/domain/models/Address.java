package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.AddressEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    private UUID id;

    private String name;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String reference;

    private Client client;

    public Address(AddressEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.street = entity.getStreet();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.zipCode = entity.getZipCode();
        this.country = entity.getCountry();
        this.reference = entity.getReference();
        this.client = new Client(entity.getClient());
    }
}