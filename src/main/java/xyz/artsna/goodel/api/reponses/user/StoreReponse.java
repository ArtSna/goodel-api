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

        public String street;
        @JsonProperty("street_number") public Integer streetNumber;
        @JsonProperty("complementary_address") public String complementaryAddress;
        public String city;
        public String state;
        @JsonProperty("zip_code") public String zipCode;
        public String country;
        public String reference;

        public boolean active;

        public Single(Store store) {
            this.id = store.getId();
            this.domain = store.getDomain();
            this.customDomain = store.getCustomDomain();
            this.name = store.getName();
            this.description = store.getDescription();
            this.contactEmail = store.getContactEmail();
            this.contactPhone = store.getContactPhone();
            this.street = store.getStreet();
            this.streetNumber = store.getStreetNumber();
            this.complementaryAddress = store.getComplementaryAddress();
            this.city = store.getCity();
            this.state = store.getState();
            this.zipCode = store.getZipCode();
            this.country = store.getCountry();
            this.reference = store.getReference();
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
