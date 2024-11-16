package xyz.artsna.goodel.infra.database.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import xyz.artsna.goodel.infra.database.entities.EmployeeEntity;

import java.util.UUID;

@Transactional
@ApplicationScoped
public class EmployeeRepository implements PanacheRepositoryBase<EmployeeEntity, UUID> {
}
