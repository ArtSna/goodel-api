package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;

import java.util.UUID;

@Entity
@Table(name = "goodel-employees")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EmployeeEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(name = "first_name", nullable = false) private String firstName;
    @Column(name = "last_name") private String lastName;

    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String password;

    @Column(nullable = false) private EmployeeFunction function;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    public EmployeeEntity(StoreEntity store, EmployeeFunction function, String firstName, String lastName, String email, String password) {
        this.id = UUID.randomUUID();
        this.function = function;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.store = store;
    }
}
