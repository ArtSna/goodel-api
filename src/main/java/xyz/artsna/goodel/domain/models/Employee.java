package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.artsna.goodel.infra.database.entities.EmployeeEntity;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Employee {

    private UUID id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    private EmployeeFunction function;

    private Store store;

    public Employee(EmployeeEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.function = entity.getFunction();
        this.store = new Store(entity.getStore());
        this.password = entity.getPassword();
    }
}