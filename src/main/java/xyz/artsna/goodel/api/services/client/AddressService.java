package xyz.artsna.goodel.api.services.client;

import jakarta.inject.Inject;
import xyz.artsna.goodel.api.requests.client.AddressRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.ForbiddenException;
import xyz.artsna.goodel.domain.models.Address;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;
import xyz.artsna.goodel.infra.database.repositories.AddressRepository;
import xyz.artsna.goodel.infra.database.repositories.ClientRepository;

import java.util.UUID;

public class AddressService {

    @Inject
    ClientRepository clients;

    @Inject
    AddressRepository addresses;

    public Address create(UUID clientId, AddressRequest.Create request) {
        ClientEntity client = clients.findByIdOptional(clientId).orElseThrow(() -> new ForbiddenException("Client is not valid"));

        if(addresses.find("name", request.getName()).count() > 0)
            throw new ConflictException("This address name already exists");

        return new Address(addresses.create(client, request.getName(), request.getStreet(), request.getCity(), request.getState(), request.getCountry(), request.getZipCode(), request.getReference()));
    }
}