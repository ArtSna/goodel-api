package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.artsna.goodel.infra.database.entities.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Store {

    private UUID id;

    private String domain;
    private String customDomain;

    private String name;
    private String description;

    private String contactEmail;
    private String contactPhone;

    private StoreAddress storeAddress;

    private boolean active;

    private List<Employee> employees;
    private List<Client> clients;
    private List<Neighborhood> neighborhoods;

    private User owner;

    public Store(StoreEntity store) {
        this.id = store.getId();
        this.domain = store.getDomain();
        this.customDomain = store.getCustomDomain();
        this.name = store.getName();
        this.description = store.getDescription();
        this.contactEmail = store.getContactEmail();
        this.contactPhone = store.getContactPhone();
        this.storeAddress = new StoreAddress(
                store.getAddressStreet(),
                store.getAddressCity(),
                store.getAddressState(),
                store.getAddressZipCode(),
                store.getAddressCountry(),
                store.getAddressNumber(),
                store.getAddressComplement(),
                store.getAddressNeighborhood(),
                store.getAddressReference()
        );
        this.active = store.isActive();
        this.owner = new User(store.getOwner());
        this.employees = store.getEmployees().stream().map(Employee::new).toList();
        this.clients = store.getClients().stream().map(Client::new).toList();
        this.neighborhoods = store.getNeighborhoods().stream().map(Neighborhood::new).toList();
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class StoreAddress {

        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
        private Integer number;
        private String complement;
        private String neighborhood;
        private String reference;

    }
}