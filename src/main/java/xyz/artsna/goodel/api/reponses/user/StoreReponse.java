package xyz.artsna.goodel.api.reponses.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import xyz.artsna.goodel.domain.models.Store;

import java.util.List;
import java.util.UUID;

public class StoreReponse {

    @JsonRootName("store")
    public static class Single {
        public UUID id;

        public String domain;
        @JsonProperty("custom_domain") public String customDomain;

        public String name;
        public String description;

        @JsonProperty("contact_email") public String contactEmail;
        @JsonProperty("contact_phone") public String contactPhone;

        @JsonProperty("address_street")  private String addressStreet;
        @JsonProperty("address_number") private Integer addressNumber;
        @JsonProperty("address_complement") private String addressComplement;
        @JsonProperty("address_city") private String addressCity;
        @JsonProperty("address_state") private String addressState;
        @JsonProperty("address_zip_code") private String addressZipCode;
        @JsonProperty("address_reference") private String addressReference;
        @JsonProperty("address_country") private String addressCountry;
        @JsonProperty("address_neighborhood") private String addressNeighborhood;

        public boolean active;

        public Single(Store store) {
            this.id = store.getId();
            this.domain = store.getDomain();
            this.customDomain = store.getCustomDomain();
            this.name = store.getName();
            this.description = store.getDescription();
            this.contactEmail = store.getContactEmail();
            this.contactPhone = store.getContactPhone();
            this.addressStreet = store.getStoreAddress().getStreet();
            this.addressNumber = store.getStoreAddress().getNumber();
            this.addressComplement = store.getStoreAddress().getComplement();
            this.addressCity = store.getStoreAddress().getCity();
            this.addressState = store.getStoreAddress().getState();
            this.addressZipCode = store.getStoreAddress().getZipCode();
            this.addressReference = store.getStoreAddress().getReference();
            this.addressCountry = store.getStoreAddress().getCountry();
            this.addressNeighborhood = store.getStoreAddress().getNeighborhood();
            this.active = store.isActive();
        }
    }

    @JsonRootName("stores")
    public static class Multiple {

        public Iterable<Single> stores;
        public int count;

        public Multiple(List<Store> stores) {
            this.stores = stores.stream().map(Single::new).toList();
            this.count = stores.size();
        }

    }
}