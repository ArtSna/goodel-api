package xyz.artsna.goodel.api.requests.client;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class AddressRequest {

    @Getter
    public static class Create {

        @NotBlank(message = "Name must be not blank")
        private String name;

        @NotBlank(message = "Street must be not blank")
        private String street;

        @NotBlank(message = "City must be not blank")
        private String city;

        @NotBlank(message = "State must be not blank")
        private String state;

        @NotBlank(message = "Country must be not blank")
        private String country;

        @NotBlank(message = "Zip Code must be not blank")
        private String zipCode;

        private String reference;
    }

    @Getter
    public static class Update {
        private String name;
        private String street;
        private String city;
        private String state;
        private String country;
        private String zipCode;
        private String reference;
    }

}