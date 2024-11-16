package xyz.artsna.goodel.api.controllers.user;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import xyz.artsna.goodel.api.reponses.auth.AuthResponse;
import xyz.artsna.goodel.api.requests.auth.AuthRequest;
import xyz.artsna.goodel.api.services.auth.AuthService;
import xyz.artsna.goodel.domain.providers.JwtTokenProvider;

@Path("/v1/user/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject AuthService authService;
    @Inject JwtTokenProvider tokenProvider;

    @POST
    @Path("/login")
    public Response login(@Valid AuthRequest.Login request) {
        var user = authService.loginUser(request);
        var token = tokenProvider.create(user);

        return Response.ok(new AuthResponse.Login(user, token)).build();
    }

    @POST
    @Path("/register")
    public Response register(@Valid AuthRequest.Register request) {
        var user = authService.registerUser(request);
        var token = tokenProvider.create(user);

        return Response.ok(new AuthResponse.Login(user, token)).build();
    }
}
