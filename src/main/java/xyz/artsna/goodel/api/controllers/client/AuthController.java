package xyz.artsna.goodel.api.controllers.client;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import xyz.artsna.goodel.api.reponses.auth.AuthResponse;
import xyz.artsna.goodel.api.requests.auth.AuthRequest;
import xyz.artsna.goodel.api.services.auth.AuthService;
import xyz.artsna.goodel.domain.providers.JwtTokenProvider;

import java.util.UUID;

@Path("/v1/client/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;
    @Inject
    JwtTokenProvider tokenProvider;

    @POST
    @Path("/login/{storeId}")
    public Response login(@PathParam("storeId") UUID storeId, @Valid AuthRequest.Login request) {
        var client = authService.loginClient(storeId, request);
        var token = tokenProvider.create(client);

        return Response.ok(new AuthResponse.Login(client, token)).build();
    }

    @POST
    @Path("/register/{storeId}")
    public Response register(@PathParam("storeId") UUID storeId, @Valid AuthRequest.Register request) {
        var client = authService.registerClient(storeId, request);
        var token = tokenProvider.create(client);

        return Response.ok(new AuthResponse.Login(client, token)).build();
    }
}
