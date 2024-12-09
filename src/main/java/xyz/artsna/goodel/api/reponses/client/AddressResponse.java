package xyz.artsna.goodel.api.reponses.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import xyz.artsna.goodel.api.reponses.store.NeighborhoodResponse;
import xyz.artsna.goodel.domain.models.Address;

import java.util.List;
import java.util.UUID;

public class AddressResponse {
    @JsonRootName("address")
    public static class Single {
        public UUID id;
        public String name;
        public String street;
        public String city;
        public String state;
        @JsonProperty("zip_code") public String zipCode;
        public String country;
        public String reference;
        public NeighborhoodResponse.Single neighborhood;

        public Single(Address address) {
            this.id = address.getId();
            this.name = address.getName();
            this.street = address.getStreet();
            this.city = address.getCity();
            this.state = address.getState();
            this.zipCode = address.getZipCode();
            this.country = address.getCountry();
            this.reference = address.getReference();
            this.neighborhood = new NeighborhoodResponse.Single(address.getNeighborhood());
        }
    }

    @JsonRootName("addresses")
    public static class Multiple {

        public Iterable<Single> addresses;
        public int count;

        public Multiple(List<Address> addresses) {
            this.addresses = addresses.stream().map(Single::new).toList();
            this.count = addresses.size();
        }

    }
}
