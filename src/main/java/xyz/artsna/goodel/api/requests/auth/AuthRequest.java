package xyz.artsna.goodel.api.requests.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AuthRequest {

    @Getter
    public static class Login {
        @NotBlank(message = "Email can't be blank")
        private String email;
        @NotBlank(message = "Password can't be blank")
        private String password;
    }

    @Getter
    public static class Register {
        @NotBlank(message = "First Name can't be blank")
        @JsonProperty("first_name") private String firstName;
        @JsonProperty("last_name") private String lastName;
        @NotBlank(message = "Email can't be blank")
        private String email;
        @NotBlank(message = "Password can't be blank")
        private String password;

        private String phone;
    }
}
