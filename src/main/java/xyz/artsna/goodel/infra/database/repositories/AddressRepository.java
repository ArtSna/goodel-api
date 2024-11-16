package xyz.artsna.goodel.infra.database.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import xyz.artsna.goodel.infra.database.entities.AddressEntity;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@ApplicationScoped
public class AddressRepository implements PanacheRepositoryBase<AddressEntity, UUID> {

}
