package xyz.artsna.goodel.infra.database.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;

import java.util.UUID;

public class ClientRepository implements PanacheRepositoryBase<ClientEntity, UUID> {

}
