package xyz.artsna.goodel.api.services.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import xyz.artsna.goodel.api.requests.auth.AuthRequest;
import xyz.artsna.goodel.domain.exceptions.ConflictException;
import xyz.artsna.goodel.domain.exceptions.NotFoundException;
import xyz.artsna.goodel.domain.exceptions.UnauthorizedException;
import xyz.artsna.goodel.domain.models.Client;
import xyz.artsna.goodel.domain.models.Employee;
import xyz.artsna.goodel.domain.models.User;
import xyz.artsna.goodel.infra.database.entities.ClientEntity;
import xyz.artsna.goodel.infra.database.entities.EmployeeEntity;
import xyz.artsna.goodel.infra.database.entities.StoreEntity;
import xyz.artsna.goodel.infra.database.entities.UserEntity;
import xyz.artsna.goodel.infra.database.repositories.ClientRepository;
import xyz.artsna.goodel.infra.database.repositories.EmployeeRepository;
import xyz.artsna.goodel.infra.database.repositories.StoreRepository;
import xyz.artsna.goodel.infra.database.repositories.UserRepository;

import java.util.UUID;

@ApplicationScoped
public class AuthService {

    @Inject StoreRepository stores;
    @Inject UserRepository users;
    @Inject ClientRepository clients;
    @Inject EmployeeRepository employees;

    public User loginUser(AuthRequest.Login request) {
        UserEntity entity = users.find("email", request.getEmail()).firstResultOptional().orElseThrow(() -> new NotFoundException("Email was not found"));

        BCrypt.Result result = BCrypt.verifyer().verify(request.getPassword().toCharArray(), entity.getPassword());

        if(!result.verified)
            throw new UnauthorizedException("Invalid password");

        return new User(entity);
    }

    public User registerUser(AuthRequest.Register request) {
        if(users.find("email", request.getEmail()).count() > 0)
            throw new ConflictException("Email already exists");

        String password = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());

        UserEntity entity = new UserEntity(request.getFirstName(), request.getLastName(), request.getEmail(), password);
        users.persistAndFlush(entity);

        return new User(entity);
    }

    public Client loginClient(UUID storeId, AuthRequest.Login request) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        ClientEntity entity = clients.find("store = ?1 and email = ?2", store, request.getEmail()).firstResultOptional().orElseThrow(() -> new NotFoundException("Email was not found"));

        BCrypt.Result result = BCrypt.verifyer().verify(request.getPassword().toCharArray(), entity.getPassword());

        if(!result.verified)
            throw new UnauthorizedException("Invalid password");

        return new Client(entity);
    }

    public Client registerClient(UUID storeId, AuthRequest.Register request) {
        if(request.getPhone() == null)
            throw new ValidationException("Phone number is required");

        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        if(clients.find("store = ?1 and email = ?2", store, request.getEmail()).count() > 0)
            throw new ConflictException("Email already exists");

        String password = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());

        ClientEntity entity = new ClientEntity(store, request.getFirstName(), request.getLastName(), request.getEmail(), password, request.getPhone());
        clients.persistAndFlush(entity);

        return new Client(entity);
    }


    public Employee loginEmployee(UUID storeId, AuthRequest.Login request) {
        StoreEntity store = stores.findByIdOptional(storeId).orElseThrow(() -> new NotFoundException("Store not found"));
        EmployeeEntity entity = employees.find("store = ?1 and email = ?2", store, request.getEmail()).firstResultOptional().orElseThrow(() -> new NotFoundException("Email was not found"));

        BCrypt.Result result = BCrypt.verifyer().verify(request.getPassword().toCharArray(), entity.getPassword());

        if(!result.verified)
            throw new UnauthorizedException("Invalid password");

        return new Employee(entity);
    }
}
