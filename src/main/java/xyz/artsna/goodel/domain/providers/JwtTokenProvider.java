package xyz.artsna.goodel.domain.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jetbrains.annotations.NotNull;
import xyz.artsna.goodel.domain.exceptions.UnauthorizedException;
import xyz.artsna.goodel.domain.models.Client;
import xyz.artsna.goodel.domain.models.Employee;
import xyz.artsna.goodel.domain.models.User;
import xyz.artsna.goodel.infra.security.Subject;
import xyz.artsna.goodel.infra.security.SubjectType;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class JwtTokenProvider {

    private final String issuer;
    private final Integer expirationTimeInMinutes;
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;

    public JwtTokenProvider(
            @ConfigProperty(name = "jwt.issuer") String issuer,
            @ConfigProperty(name = "jwt.secret") String secret,
            @ConfigProperty(name = "jwt.expiration.time.minutes") Integer expirationTimeInMinutes
    ){
        this.issuer = issuer;
        this.algorithm = Algorithm.HMAC512(secret);
        this.jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        this.expirationTimeInMinutes = expirationTimeInMinutes;
    }

    private String create(User user) {
        return create(SubjectType.USER, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public String create(Client client) {
        return create(SubjectType.CLIENT, client.getId(), client.getFirstName(), client.getLastName(), client.getEmail());
    }

    public String create(Employee employee) {
        SubjectType type = null;

        switch (employee.getFunction()) {
            case DELIVERY_MAN -> type = SubjectType.DELIVERY_MAN;
            case CLERK -> type = SubjectType.CLERK;
            case CASHIER -> type = SubjectType.CASHIER;
        }

        if (type == null)
            throw new IllegalArgumentException("Invalid employee function");

        return create(type, employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }

    private String create(SubjectType type, UUID id, String firstName, String lastName, String email) {
        JWTCreator.Builder builder = JWT.create().withIssuer(issuer).withIssuedAt(new Date())
                .withSubject(id.toString())
                .withClaim("first_name", firstName)
                .withClaim("last_name", lastName)
                .withClaim("email", email)
                .withClaim("type", type.toString());

        if (expirationTimeInMinutes != null)
            builder.withExpiresAt(plusMinutes(expirationTimeInMinutes));

        return builder.sign(algorithm);
    }

    public Subject verify(String token) {
        DecodedJWT decodedJWT;

        try{
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new UnauthorizedException(e.getMessage());
        }

        return new Subject(
                UUID.fromString(decodedJWT.getSubject()),
                decodedJWT.getClaim("first_name").asString(),
                decodedJWT.getClaim("last_name").asString(),
                decodedJWT.getClaim("email").asString(),
                SubjectType.valueOf(decodedJWT.getClaim("type").asString())
        );
    }

    private static @NotNull Date plusMinutes(int minutes) {
        long oneMinuteInMillis = 60000;
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTimeInMillis() + (minutes * oneMinuteInMillis));
    }
}
