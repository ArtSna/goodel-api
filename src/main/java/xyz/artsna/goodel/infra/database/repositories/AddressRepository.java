package xyz.artsna.goodel.infra.database.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import xyz.artsna.goodel.infra.database.entities.AddressEntity;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;

import java.util.UUID;


public class AddressRepository implements PanacheRepositoryBase<AddressEntity, UUID> {

    public AddressEntity create(ClientEntity client, String name, String street, String city, String state, String country, String zipCode, String reference) {
        AddressEntity entity = new AddressEntity(client, name, street, city, state, zipCode, country, reference);
        persistAndFlush(entity);
        return entity;
    }

}
