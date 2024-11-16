package xyz.artsna.goodel.api.services.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import xyz.artsna.goodel.api.requests.user.StoreRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.NotFoundException;
import xyz.artsna.goodel.domain.models.Store;
import xyz.artsna.goodel.infra.database.entities.StoreEntity;
import xyz.artsna.goodel.infra.database.entities.UserEntity;
import xyz.artsna.goodel.infra.database.repositories.StoreRepository;
import xyz.artsna.goodel.infra.database.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StoreService {

    @Inject
    UserRepository users;

    @Inject
    StoreRepository stores;

    public Store create(UUID ownerId, StoreRequest.Create request) {
        UserEntity owner = users.findById(ownerId);

        if(stores.find("name = ?1 and owner = ?2", request.getName(), owner).count() > 0)
            throw new ConflictException("This store name already exists");

        var entity = new StoreEntity(owner, request.getDomain(), request.getCustomDomain(), request.getName(), request.getDescription(), request.getContactEmail(), request.getContactPhone(), request.getStreet(), request.getStreetNumber(), request.getComplementaryAddress(), request.getCity(), request.getState(), request.getZipCode(), request.getCountry(), request.getReference());
        stores.persistAndFlush(entity);

        return new Store(entity);
    }

    public Store getById(UUID ownerId, UUID id) {
        UserEntity owner = users.findById(ownerId);
        return new Store(stores.find("owner = ?1 and id = ?2", owner, id).firstResultOptional().orElseThrow(() -> new NotFoundException("Store not found")));
    }

    public List<Store> getStores(UUID ownerId) {
        UserEntity owner = users.findById(ownerId);
        return stores.find("owner", owner).stream().map(Store::new).toList();
    }

    @Transactional
    public Store update(UUID ownerId, UUID id, StoreRequest.Update request) {
        UserEntity owner = users.findById(ownerId);
        StoreEntity store = stores.find("owner = ?1 and id = ?2", owner, id).firstResultOptional().orElseThrow(() -> new NotFoundException("Store not found"));

        if(request.getDomain() != null)
            store.setDomain(request.getDomain());
        if(request.getCustomDomain() != null)
            store.setCustomDomain(request.getCustomDomain());
        if(request.getName() != null)
            store.setName(request.getName());
        if(request.getDescription() != null)
            store.setDescription(request.getDescription());
        if(request.getContactEmail() != null)
            store.setContactEmail(request.getContactEmail());
        if(request.getContactPhone() != null)
            store.setContactPhone(request.getContactPhone());
        if(request.getStreet() != null)
            store.setStreet(request.getStreet());
        if(request.getStreetNumber() != null)
            store.setStreetNumber(request.getStreetNumber());
        if(request.getComplementaryAddress() != null)
            store.setComplementaryAddress(request.getComplementaryAddress());
        if(request.getCity() != null)
            store.setCity(request.getCity());
        if(request.getState() != null)
            store.setState(request.getState());
        if(request.getZipCode() != null)
            store.setZipCode(request.getZipCode());
        if(request.getCountry() != null)
            store.setCountry(request.getCountry());
        if(request.getReference() != null)
            store.setReference(request.getReference());

        store.persistAndFlush();

        return new Store(store);
    }

    public void delete(UUID ownerId, UUID id) {
        UserEntity owner = users.findById(ownerId);
        StoreEntity store = stores.find("owner =?1 and id =?2", owner, id).firstResultOptional().orElseThrow(() -> new NotFoundException("Store not found"));
        stores.delete(store);
    }
}
