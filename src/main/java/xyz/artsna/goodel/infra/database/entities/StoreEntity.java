package xyz.artsna.goodel.infra.database.entities;

import io.quarkus.arc.impl.Sets;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "goodel-stores")
@Data
@NoArgsConstructor
public class StoreEntity extends PanacheEntityBase {

    @Id private UUID id = UUID.randomUUID();

    @Column(nullable = false, unique = true) private String domain;
    @Column(name = "custom_domain") private String customDomain;

    @Column(nullable = false) private String name;
    private String description;

    @Column(name = "contact_email") private String contactEmail;
    @Column(nullable = false, name = "contact_phone") private String contactPhone;

    @Column(nullable = false, name = "address_street") private String addressStreet;
    @Column(nullable = false, name = "address_number") private Integer addressNumber;
    @Column(nullable = false, name = "address_city") private String addressCity;
    @Column(nullable = false, name = "address_state") private String addressState;
    @Column(nullable = false, name = "address_zip_code") private String addressZipCode;
    @Column(nullable = false, name = "address_country") private String addressCountry;
    @Column(nullable = false, name = "address_neighborhood") private String addressNeighborhood;
    @Column(name = "address_complement") private String addressComplement;
    @Column(name = "address_reference") private String addressReference;

    private boolean active = true;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store", fetch = FetchType.LAZY)
    private Set<EmployeeEntity> employees = Sets.of();

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store", fetch = FetchType.LAZY)
    private Set<ClientEntity> clients = Sets.of();

    @OneToMany(cascade= CascadeType.ALL, mappedBy="store", fetch = FetchType.LAZY)
    private Set<NeighborhoodEntity> neighborhoods = Sets.of();

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private UserEntity owner;

    public StoreEntity(UserEntity owner, String domain, String customDomain, String name, String description, String contactEmail, String contactPhone, String addressStreet, Integer addressNumber, String addressComplement, String addressCity, String addressState, String addressZipCode, String addressCountry, String addressReference, String addressNeighborhood) {
        this.owner = owner;
        this.domain = domain;
        this.customDomain = customDomain;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;

        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
        this.addressReference = addressReference;
        this.addressNeighborhood = addressNeighborhood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use apenas campos simples (como `id`)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StoreEntity that = (StoreEntity) obj;
        return Objects.equals(id, that.getId()); // Compare apenas identificadores únicos
    }
}