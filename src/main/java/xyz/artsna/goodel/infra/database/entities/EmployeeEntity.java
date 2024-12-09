package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "goodel-employees")
@Data
@NoArgsConstructor
public class EmployeeEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @Column(name = "first_name", nullable = false) private String firstName;
    @Column(name = "last_name") private String lastName;

    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String password;

    @Column(nullable = false) private EmployeeFunction function;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    public EmployeeEntity(StoreEntity store, EmployeeFunction function, String firstName, String lastName, String email, String password) {
        this.function = function;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.store = store;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}
