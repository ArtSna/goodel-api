package xyz.artsna.goodel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Client {
    private UUID id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    private String phone;

    private List<Address> addresses;

    public Client(ClientEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
        this.addresses = entity.getAddresses().stream().map(Address::new).toList();
    }


}
