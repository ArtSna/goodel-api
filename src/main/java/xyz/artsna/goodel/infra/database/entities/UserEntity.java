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
@Table(name = "goodel-users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserEntity extends PanacheEntityBase {

    @Id private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true) private String email;
    private String password;

    @OneToMany(targetEntity = StoreEntity.class, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<StoreEntity> stores;

    public UserEntity(String firstName, String lastName, String email, String password) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
