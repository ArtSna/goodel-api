package xyz.artsna.goodel.api.controllers.store;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import xyz.artsna.goodel.api.requests.store.ClientRequest;
import xyz.artsna.goodel.api.services.store.ClientService;

import java.util.UUID;

@Path("/v1/store/{storeId}/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {

    @PathParam("storeId")
    UUID storeId;

    @Inject
    ClientService clients;

    @GET
    @Path("/{clientId}")
    @RolesAllowed("")
    public Response getClient(@PathParam("clientId") UUID clientId) {
        return Response.ok().build();
    }

    @GET
    public Response getAllClients() {
        return Response.ok().build();
    }

    @POST
    public Response create(@Valid ClientRequest.Create request) {
        return Response.ok().build();
    }

    @PUT
    @Path("/{clientId}")
    public Response update(@PathParam("clientId") UUID clientId, @Valid ClientRequest.Update request) {
        return Response.ok().build();
    }

    @DELETE
    @Path("/{clientId}")
    public Response delete(@PathParam("clientId") UUID clientId) {
        return Response.noContent().build();
    }
}