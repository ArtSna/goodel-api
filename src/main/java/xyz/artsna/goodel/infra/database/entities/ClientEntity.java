package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "goodel_clients")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ClientEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false, name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;

    @Column(nullable = false, unique = true) private String email;
    private String password;

    @Column(nullable = false) private String phone;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="client")
    private Set<AddressEntity> addresses;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="client")
    private Set<OrderEntity> orders;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false, updatable=false)
    private StoreEntity store;

    public ClientEntity(StoreEntity store, String firstName, String lastName, String email, String password, String phone) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.store = store;
        this.addresses = Set.of();
    }

}