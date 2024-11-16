package xyz.artsna.goodel.api.controllers.client;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import xyz.artsna.goodel.api.controllers.AbstractControllerBase;
import xyz.artsna.goodel.api.reponses.client.AddressResponse;
import xyz.artsna.goodel.api.requests.client.AddressRequest;
import xyz.artsna.goodel.api.services.client.AddressService;

import java.util.UUID;

@Path("/v1/client/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressController extends AbstractControllerBase {

    @Inject AddressService addresses;

    @GET
    @Path("/{id}")
    @RolesAllowed("CLIENT")
    public Response getById(@Context SecurityContext context, @PathParam("id") UUID id) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new AddressResponse.Single(addresses.getById(subject.getId(), id))).build();
    }

    @GET
    @RolesAllowed("CLIENT")
    public Response getById(@Context SecurityContext context) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new AddressResponse.Multiple(addresses.getAllByClient(subject.getId()))).build();
    }


    @POST
    @RolesAllowed("CLIENT")
    public Response create(@Context SecurityContext context, @Valid AddressRequest.Create request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new AddressResponse.Single(addresses.create(subject.getId(), request))).build();
    }

    @PUT
    @RolesAllowed("CLIENT")
    public Response update(@Context SecurityContext context, @Valid AddressRequest.Update request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new AddressResponse.Single(addresses.update(subject.getId(), request))).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("CLIENT")
    public Response delete(@Context SecurityContext context, @PathParam("id") UUID id) {
        var subject = validateSubject(context.getUserPrincipal());
        addresses.delete(subject.getId(), id);
        return Response.noContent().build();
    }
}