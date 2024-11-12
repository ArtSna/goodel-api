package xyz.artsna.goodel.api.controllers.client;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import xyz.artsna.goodel.api.reponses.client.AddressResponse;
import xyz.artsna.goodel.api.requests.client.AddressRequest;
import xyz.artsna.goodel.api.services.client.AddressService;
import xyz.artsna.goodel.infra.security.Subject;

import java.util.UUID;

@Path("/v1/client/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressController {

    @Inject AddressService addresses;

    @POST
    @RolesAllowed("CLIENT")
    public Response create(@Context SecurityContext context, @Valid AddressRequest.Create request) {
        var subject = (Subject) context.getUserPrincipal();
        return Response.ok(new AddressResponse.Single(addresses.create(subject.getId(), request))).build();
    }

    @PUT
    @RolesAllowed("CLIENT")
    public void update(@Valid AddressRequest.Update request) {

    }

    @GET
    @Path("/{id}")
    @RolesAllowed("CLIENT")
    public void getById(@PathParam("id") UUID id) {

    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("CLIENT")
    public void delete(@PathParam("id") UUID id) {

    }


}
