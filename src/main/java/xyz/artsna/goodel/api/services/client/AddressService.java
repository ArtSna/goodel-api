package xyz.artsna.goodel.api.services.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import xyz.artsna.goodel.api.requests.client.AddressRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.NotFoundException;
import xyz.artsna.goodel.domain.models.Address;
import xyz.artsna.goodel.domain.models.Neighborhood;
import xyz.artsna.goodel.infra.database.entities.AddressEntity;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;
import xyz.artsna.goodel.infra.database.repositories.AddressRepository;
import xyz.artsna.goodel.infra.database.repositories.ClientRepository;
import xyz.artsna.goodel.infra.database.repositories.NeighborhoodRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AddressService {

    @Inject
    ClientRepository clients;

    @Inject
    AddressRepository addresses;

    @Inject
    NeighborhoodRepository neighborhoods;

    public Address create(UUID clientId, AddressRequest.Create request) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new NotFoundException("Client not found"));

        if(addresses.find("name=?1 and client=?2", request.getName(), client).count() > 0)
            throw new ConflictException("This address name already exists");

        var neighborhood = neighborhoods.find("store=?1 and id=?2", client.getStore(), UUID.fromString(request.getNeighborhood())).firstResultOptional().orElseThrow(() -> new NotFoundException("Neighborhood not found"));

        var entity = new AddressEntity(client, neighborhood, request.getName(), request.getStreet(), request.getNumber(), request.getCity(), request.getState(), request.getCountry(), request.getZipCode(), request.getReference(), request.getComplement());
        addresses.persistAndFlush(entity);

        return new Address(entity);
    }

    public Address getById(UUID clientId, UUID addressId) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
        return addresses.find("client=?1 and id=?2", client, addressId).firstResultOptional().map(Address::new).orElseThrow(() -> new NotFoundException("Address not found"));
    }

    public List<Address> getAllByClient(UUID clientId) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
        return addresses.find("client", client).stream().map(Address::new).toList();
    }

    public Address update(UUID clientId, UUID addressId, AddressRequest.Update request) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
        var entity = addresses.find("client=?1 and id=?2", client, addressId).firstResultOptional().orElseThrow(() -> new NotFoundException("Address not found"));

        if(request.getName() != null)
            entity.setName(request.getName());
        if(request.getStreet() != null)
            entity.setStreet(request.getStreet());
        if(request.getCity() != null)
            entity.setCity(request.getCity());
        if(request.getState() != null)
            entity.setState(request.getState());
        if(request.getCountry() != null)
            entity.setCountry(request.getCountry());
        if(request.getZipCode() != null)
            entity.setZipCode(request.getZipCode());
        if(request.getReference() != null)
            entity.setReference(request.getReference());
        if(request.getNeighborhood() != null) {
            var neighborhood = neighborhoods.find("store=?1 and id=?2", client.getStore(), UUID.fromString(request.getNeighborhood())).firstResultOptional().orElseThrow(() -> new NotFoundException("Neighborhood not found"));
            entity.setNeighborhood(neighborhood);
        }
        if(request.getComplement() != null)
            entity.setComplement(request.getComplement());
        if(request.getNumber() != null)
            entity.setNumber(request.getNumber());

        addresses.persistAndFlush(entity);

        return new Address(entity);
    }

    public void delete(UUID clientId, UUID addressId) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new NotFoundException("Client not found"));

        if(addresses.delete("client=?1 and id=?2", client, addressId) == 0)
            throw new NotFoundException("Address not found");
    }
}