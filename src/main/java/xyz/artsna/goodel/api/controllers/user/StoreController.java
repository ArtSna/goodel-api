package xyz.artsna.goodel.api.controllers.user;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import xyz.artsna.goodel.api.controllers.AbstractControllerBase;
import xyz.artsna.goodel.api.reponses.user.StoreReponse;
import xyz.artsna.goodel.api.requests.user.StoreRequest;
import xyz.artsna.goodel.api.services.user.StoreService;

import java.util.UUID;

@Path("/v1/user/store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoreController extends AbstractControllerBase {

    @Inject StoreService stores;

    @POST
    @RolesAllowed("USER")
    public Response create(@Context SecurityContext context, @Valid StoreRequest.Create request){
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new StoreReponse.Single(stores.create(subject.getId(), request))).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("USER")
    public Response getStore(@Context SecurityContext context, @PathParam("id") UUID id){
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new StoreReponse.Single(stores.getById(subject.getId(), id))).build();
    }

    @GET
    @RolesAllowed("USER")
    public Response getAllStores(@Context SecurityContext context){
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new StoreReponse.Multiple(stores.getStores(subject.getId()))).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("USER")
    public Response update(@Context SecurityContext context, @PathParam("id") UUID id, @Valid StoreRequest.Update request){
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok(new StoreReponse.Single(stores.update(subject.getId(), id, request))).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("USER")
    public Response delete(@Context SecurityContext context, @PathParam("id") UUID id){
        var subject = validateSubject(context.getUserPrincipal());
        stores.delete(subject.getId(), id);
        return Response.noContent().build();
    }
}
