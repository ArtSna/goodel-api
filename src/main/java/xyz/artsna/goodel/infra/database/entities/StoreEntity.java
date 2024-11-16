package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-stores")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StoreEntity extends PanacheEntityBase {

    @Id private UUID id;

    @Column(nullable = false, unique = true) private String domain;
    @Column(name = "custom_domain") private String customDomain;

    @Column(nullable = false) private String name;
    private String description;

    @Column(name = "contact_email") private String contactEmail;
    @Column(nullable = false, name = "contact_phone") private String contactPhone;

    @Column(nullable = false) private String street;
    @Column(nullable = false, name = "street_number") private Integer streetNumber;
    @Column(name = "complementary_address") private String complementaryAddress;
    @Column(nullable = false) private String city;
    @Column(nullable = false) private String state;
    @Column(nullable = false, name = "zip_code") private String zipCode;
    @Column(nullable = false) private String country;
    private String reference;

    private boolean active = true;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store", fetch = FetchType.LAZY)
    private Set<EmployeeEntity> employees = Sets.of();

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store", fetch = FetchType.LAZY)
    private Set<ClientEntity> clients = Sets.of();

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private UserEntity owner;

    public StoreEntity(UserEntity owner, String domain, String customDomain, String name, String description, String contactEmail, String contactPhone, String street, Integer streetNumber, String complementaryAddress, String city, String state, String zipCode, String country, String reference) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.domain = domain;
        this.customDomain = customDomain;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.complementaryAddress = complementaryAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.reference = reference;
    }
}