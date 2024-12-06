package xyz.artsna.goodel.api.requests.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.UUID;

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
        @JsonProperty("zip_code")
        private String zipCode;

        private Integer number;

        private String complement;

        @UUID(message = "Neighborhood must be a valid uuid", allowNil = false)
        private String neighborhood;

        private String reference;
    }

    @Getter
    public static class Update {
        private String name;
        private String street;
        private String city;
        private String state;
        @JsonProperty("zip_code")
        private String zipCode;
        private Integer number;
        private String country;
        private String complement;
        private String reference;
        @UUID(message = "Neighborhood must be a valid uuid", allowEmpty = true)
        private String neighborhood;
    }

}