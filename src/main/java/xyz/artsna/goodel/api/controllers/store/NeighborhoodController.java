package xyz.artsna.goodel.api.controllers.store;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import xyz.artsna.goodel.api.controllers.AbstractControllerBase;
import xyz.artsna.goodel.api.reponses.store.NeighborhoodResponse;
import xyz.artsna.goodel.api.requests.store.ClientRequest;
import xyz.artsna.goodel.api.requests.store.NeighborhoodRequest;
import xyz.artsna.goodel.api.services.store.ClientService;
import xyz.artsna.goodel.api.services.store.NeighborhoodService;

import java.util.UUID;

@Path("/v1/store/{storeId}/neighborhood")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NeighborhoodController extends AbstractControllerBase {
    @PathParam("storeId")
    UUID storeId;

    @Inject
    NeighborhoodService neighborhoods;

    @GET
    @Path("/{neighborhoodId}")
    @RolesAllowed({"USER", "EMPLOYEE", "CLIENT"})
    public Response getNeighborhood(@Context SecurityContext context, @PathParam("neighborhoodId") UUID neighborhoodId) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new NeighborhoodResponse.Single(neighborhoods.getById(storeId, neighborhoodId))).build();
    }

    @GET
    @RolesAllowed({"USER", "EMPLOYEE", "CLIENT"})
    public Response getAllNeighborhoods(@Context SecurityContext context) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new NeighborhoodResponse.Multiple(neighborhoods.getAllByStore(storeId))).build();
    }

    @POST
    @RolesAllowed("USER")
    public Response create(@Context SecurityContext context, @Valid NeighborhoodRequest.Create request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new NeighborhoodResponse.Single(neighborhoods.create(storeId, request))).build();
    }

    @PUT
    @Path("/{neighborhoodId}")
    @RolesAllowed("USER")
    public Response update(@Context SecurityContext context, @PathParam("neighborhoodId") UUID neighborhoodId, @Valid NeighborhoodRequest.Update request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new NeighborhoodResponse.Single(neighborhoods.update(storeId, neighborhoodId, request))).build();
    }

    @DELETE
    @Path("/{neighborhoodId}")
    @RolesAllowed("USER")
    public Response delete(@Context SecurityContext context, @PathParam("neighborhoodId") UUID neighborhoodId) {
        var subject = validateSubject(context.getUserPrincipal());
        neighborhoods.delete(storeId, neighborhoodId);
        return Response.noContent().build();
    }
}
