package xyz.artsna.goodel.api.controllers.employee;

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

@Path("/v1/employee/auth")
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
        var employee = authService.loginEmployee(storeId, request);
        var token = tokenProvider.create(employee);

        return Response.ok(new AuthResponse.Login(employee, token)).build();
    }

}
