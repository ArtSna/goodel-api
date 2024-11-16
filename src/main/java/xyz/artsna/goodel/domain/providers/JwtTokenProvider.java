package xyz.artsna.goodel.domain.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import xyz.artsna.goodel.domain.exceptions.UnauthorizedException;
import xyz.artsna.goodel.domain.models.Client;
import xyz.artsna.goodel.domain.models.Employee;
import xyz.artsna.goodel.domain.models.User;
import xyz.artsna.goodel.infra.database.enums.EmployeeFunction;
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

    public String create(User user) {
        return create(SubjectType.USER, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), null, null);
    }

    public String create(Client client) {
        return create(SubjectType.CLIENT, client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhone(), null);
    }

    public String create(Employee employee) {
        return create(SubjectType.EMPLOYEE, employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), null, employee.getFunction());
    }

    private String create(SubjectType type, UUID id, String firstName, String lastName, String email, String phone, EmployeeFunction function) {
        JWTCreator.Builder builder = JWT.create().withIssuer(issuer).withIssuedAt(new Date())
                .withSubject(id.toString())
                .withClaim("first_name", firstName)
                .withClaim("last_name", lastName)
                .withClaim("email", email)
                .withClaim("phone", phone)
                .withClaim("type", type.toString());

        if(function != null)
            builder.withClaim("function", function.toString());

        if (expirationTimeInMinutes != null)
            builder.withExpiresAt(plusMinutes(expirationTimeInMinutes));

        return builder.sign(algorithm);
    }

    public Subject verify(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        EmployeeFunction function = null;

        if(decodedJWT.getClaims().containsKey("function"))
            function = EmployeeFunction.valueOf(decodedJWT.getClaim("function").asString());

        return new Subject(
                UUID.fromString(decodedJWT.getSubject()),
                decodedJWT.getClaim("first_name").asString(),
                decodedJWT.getClaim("last_name").asString(),
                decodedJWT.getClaim("email").asString(),
                decodedJWT.getClaim("phone").asString(),
                function,
                SubjectType.valueOf(decodedJWT.getClaim("type").asString())
        );
    }

    private static Date plusMinutes(int minutes) {
        long oneMinuteInMillis = 60000;
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTimeInMillis() + (minutes * oneMinuteInMillis));
    }
}
