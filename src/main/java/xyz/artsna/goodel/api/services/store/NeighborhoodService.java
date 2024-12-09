package xyz.artsna.goodel.api.services.store;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import xyz.artsna.goodel.api.requests.store.NeighborhoodRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.NotFoundException;
import xyz.artsna.goodel.domain.models.Neighborhood;
import xyz.artsna.goodel.infra.database.entities.NeighborhoodEntity;
import xyz.artsna.goodel.infra.database.entities.StoreEntity;
import xyz.artsna.goodel.infra.database.repositories.NeighborhoodRepository;
import xyz.artsna.goodel.infra.database.repositories.StoreRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NeighborhoodService {

    @Inject
    StoreRepository stores;

    @Inject
    NeighborhoodRepository neighborhoods;

    public Neighborhood create(UUID storeId, NeighborhoodRequest.Create request) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));

        if(neighborhoods.find("name = ?1 and store = ?2", request.getName(), store).count() > 0)
            throw new ConflictException("Neighborhood with this name already exists");

        var entity = new NeighborhoodEntity(store, request.getName(), request.getDeliveryFee());
        neighborhoods.persistAndFlush(entity);

        return new Neighborhood(entity);
    }

    public Neighborhood getById(UUID storeId, UUID neighborhoodId) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        return new Neighborhood(neighborhoods.find("store=?1 and id=?2", store, neighborhoodId).firstResultOptional().orElseThrow(() -> new NotFoundException("Neighborhood not found")));
    }

    public List<Neighborhood> getAllByStore(UUID storeId) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        return neighborhoods.find("store", store).stream().map(Neighborhood::new).toList();
    }

    @Transactional
    public Neighborhood update(UUID storeId, UUID neighborhoodId, NeighborhoodRequest.Update request) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        var entity = neighborhoods.find("store=?1 and id=?2", store, neighborhoodId).firstResultOptional().orElseThrow(() -> new NotFoundException("Neighborhood not found"));

        if(request.getName() != null)
            entity.setName(request.getName());
        if(request.getDeliveryFee() != null)
            entity.setDeliveryFee(request.getDeliveryFee());

        neighborhoods.getEntityManager().merge(entity);
        neighborhoods.flush();

        return new Neighborhood(entity);
    }

    public void delete(UUID storeId, UUID neighborhoodId) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));

        if(neighborhoods.delete("store=?1 and id=?2", store, neighborhoodId) == 0)
            throw new NotFoundException("Neighborhood not found");
    }
}