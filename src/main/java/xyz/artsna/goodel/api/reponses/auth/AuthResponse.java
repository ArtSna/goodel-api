package xyz.artsna.goodel.api.reponses.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import xyz.artsna.goodel.domain.models.Client;
import xyz.artsna.goodel.domain.models.Employee;
import xyz.artsna.goodel.domain.models.User;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;

public class AuthResponse {

    @JsonRootName("login")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Login {
        @JsonProperty("first_name")
        public String firstName;
        @JsonProperty("last_name")
        public String lastName;
        public String email;
        public String phone;
        public EmployeeFunction function;
        public String token;

        public Login(User user, String token) {
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.token = token;
        }

        public Login(Client client, String token) {
            this.firstName = client.getFirstName();
            this.lastName = client.getLastName();
            this.email = client.getEmail();
            this.phone = client.getPhone();
            this.token = token;
        }

        public Login(Employee employee, String token) {
            this.firstName = employee.getFirstName();
            this.lastName = employee.getLastName();
            this.email = employee.getEmail();
            this.function = employee.getFunction();
            this.token = token;
        }
    }

}
