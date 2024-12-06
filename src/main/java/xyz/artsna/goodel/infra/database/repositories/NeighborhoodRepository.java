package xyz.artsna.goodel.infra.database.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import xyz.artsna.goodel.infra.database.entities.NeighborhoodEntity;

import java.util.UUID;

@Transactional
@ApplicationScoped
public class NeighborhoodRepository implements PanacheRepositoryBase<NeighborhoodEntity, UUID> {
}
