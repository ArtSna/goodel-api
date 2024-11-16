package xyz.artsna.goodel.api.services.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import xyz.artsna.goodel.api.requests.client.AddressRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.NotFoundException;
import xyz.artsna.goodel.domain.models.Address;
import xyz.artsna.goodel.infra.database.entities.AddressEntity;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;
import xyz.artsna.goodel.infra.database.repositories.AddressRepository;
import xyz.artsna.goodel.infra.database.repositories.ClientRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AddressService {

    @Inject
    ClientRepository clients;

    @Inject
    AddressRepository addresses;

    public Address create(UUID clientId, AddressRequest.Create request) {
        ClientEntity client = clients.findById(clientId);

        if(addresses.find("name = ? and client = ?", request.getName(), client).count() > 0)
            throw new ConflictException("This address name already exists");

        var entity = new AddressEntity(client, request.getName(), request.getStreet(), request.getCity(), request.getState(), request.getCountry(), request.getZipCode(), request.getReference());
        addresses.persistAndFlush(entity);

        return new Address(entity);
    }

    public Address getById(UUID clientId, UUID id) {
        ClientEntity client = clients.findById(clientId);
        return addresses.find("client = ?1 and id = ?2", client, id).firstResultOptional().map(Address::new).orElseThrow(() -> new NotFoundException("Address not found"));
    }

    public List<Address> getAllByClient(UUID clientId) {
        ClientEntity client = clients.findById(clientId);
        return addresses.find("client", client).stream().map(Address::new).toList();
    }

    public Address update(UUID addressId, AddressRequest.Update request) {
        var entity = addresses.findById(addressId);

        if(entity == null)
            throw new NotFoundException("Address not found");

        entity.setName(request.getName());
        entity.setStreet(request.getStreet());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setCountry(request.getCountry());
        entity.setZipCode(request.getZipCode());
        entity.setReference(request.getReference());

        addresses.persistAndFlush(entity);

        return new Address(entity);
    }

    public void delete(UUID clientId, UUID addressId) {
        ClientEntity client = clients.findById(clientId);

        if(addresses.delete("client = ?1 and id = ?2", client, addressId) == 0)
            throw new NotFoundException("Address not found");
    }
}