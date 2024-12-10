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
@Table(name = "goodel-clients")
@Data
@NoArgsConstructor
public class ClientEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @Column(nullable = false, name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;

    @Column(nullable = false, unique = true) private String email;
    private String password;

    @Column(nullable = false) private String phone;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="client", fetch = FetchType.EAGER)
    private Set<AddressEntity> addresses = Sets.of();

    @OneToMany(cascade= CascadeType.ALL, mappedBy="client", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders = Sets.of();

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false, updatable=false)
    private StoreEntity store;

    public ClientEntity(StoreEntity store, String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
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
        ClientEntity that = (ClientEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}