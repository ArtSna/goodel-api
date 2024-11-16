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

        @NotBlank(message = "Street can't be blank")
        private String street;
        @NotNull(message = "Street Number can't be null")
        @JsonProperty("street_number")
        private Integer streetNumber;
        @JsonProperty("complementary_address")
        private String complementaryAddress;
        @NotBlank(message = "City can't be blank")
        private String city;
        @NotBlank(message = "State can't be blank")
        private String state;
        @NotBlank(message = "Zip Code can't be blank")
        @JsonProperty("zip_code")
        private String zipCode;
        private String reference;
        @NotBlank(message = "Country can't be blank")
        private String country;

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

        private String street;
        @JsonProperty("street_number") private Integer streetNumber;
        @JsonProperty("complementary_address") private String complementaryAddress;
        private String city;
        private String state;
        @JsonProperty("zip_code") private String zipCode;
        private String reference;
        private String country;

        private String domain;
        @JsonProperty("custom_domain") private String customDomain;
    }
}
