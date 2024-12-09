package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-users")
@Data
@NoArgsConstructor
public class UserEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    private String firstName;
    private String lastName;

    @Column(unique = true) private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<StoreEntity> stores = Sets.of();

    public UserEntity(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity that = (UserEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}
