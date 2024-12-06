package xyz.artsna.goodel.api.requests.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequest {

    @Getter
    public static class Create {

        @NotBlank(message = "Name can't be blank")
        private String name;
        private String description;

        @JsonProperty("contact_email")
        private String contactEmail;
        @NotBlank(message = "Contact Phone can't be blank")
        @JsonProperty("contact_phone")
        private String contactPhone;

        @NotBlank(message = "Address Street can't be blank")
        @JsonProperty("address_street")  private String addressStreet;
        @NotNull(message = "Address Number can't be null")
        @JsonProperty("address_number") private Integer addressNumber;
        @JsonProperty("address_complement") private String addressComplement;
        @NotBlank(message = "Address City can't be blank")
        @JsonProperty("address_city") private String addressCity;
        @NotBlank(message = "Address State can't be blank")
        @JsonProperty("address_state") private String addressState;
        @NotBlank(message = "Address Zip Code can't be blank")
        @JsonProperty("address_zip_code") private String addressZipCode;
        @JsonProperty("address_reference") private String addressReference;
        @NotBlank(message = "Address Country can't be blank")
        @JsonProperty("address_country") private String addressCountry;
        @NotBlank(message = "Address Neighborhood can't be blank")
        @JsonProperty("address_neighborhood") private String addressNeighborhood;

        @NotBlank(message = "Domain can't be blank")
        private String domain;
        @JsonProperty("custom_domain")
        private String customDomain;
    }

    @Getter
    public static class Update {
        private String name;
        private String description;

        @JsonProperty("contact_email") private String contactEmail;
        @JsonProperty("contact_phone") private String contactPhone;

        @JsonProperty("address_street")  private String addressStreet;
        @JsonProperty("address_number") private Integer addressNumber;
        @JsonProperty("address_complement") private String addressComplement;
        @JsonProperty("address_city") private String addressCity;
        @JsonProperty("address_state") private String addressState;
        @JsonProperty("address_zip_code") private String addressZipCode;
        @JsonProperty("address_reference") private String addressReference;
        @JsonProperty("address_country") private String addressCountry;
        @JsonProperty("address_neighborhood") private String addressNeighborhood;

        private String domain;
        @JsonProperty("custom_domain") private String customDomain;
    }
}
