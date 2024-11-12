package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.artsna.goodel.infra.database.entities.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Store {

    private UUID id;

    private String domain;
    private String customDomain;

    private String name;
    private String description;

    private String contactEmail;
    private String contactPhone;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String reference;

    private boolean active;

    private List<ProductCategory> categories;
    private List<Product> products;
    private List<Employee> employees;
    private List<Client> clients;

    private User owner;

    public Store(StoreEntity store) {
        this.id = store.getId();
        this.domain = store.getDomain();
        this.customDomain = store.getCustomDomain();
        this.name = store.getName();
        this.description = store.getDescription();
        this.contactEmail = store.getContactEmail();
        this.contactPhone = store.getContactPhone();
        this.street = store.getStreet();
        this.city = store.getCity();
        this.state = store.getState();
        this.zipCode = store.getZipCode();
        this.country = store.getCountry();
        this.reference = store.getReference();
        this.active = store.isActive();
        this.owner = new User(store.getOwner());
        this.categories = store.getCategories().stream().map(ProductCategory::new).toList();
        this.products = store.getProducts().stream().map(Product::new).toList();
        this.employees = store.getEmployees().stream().map(Employee::new).toList();
        this.clients = store.getClients().stream().map(Client::new).toList();
    }

}
