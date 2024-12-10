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
import xyz.artsna.goodel.api.requests.store.EmployeeRequest;
import xyz.artsna.goodel.api.services.store.EmployeeService;

import java.util.UUID;

@Path("/v1/store/{storeId}/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController extends AbstractControllerBase {

    @PathParam("storeId")
    UUID storeId;

    @Inject
    EmployeeService employess;

    @GET
    @Path("/{productId}")
    @RolesAllowed({"USER", "EMPLOYEE"})
    public Response get(@Context SecurityContext context, @PathParam("productId") UUID productId) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok().build();
    }

    @GET
    @RolesAllowed({"USER", "EMPLOYEE"})
    public Response getAll(@Context SecurityContext context) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok().build();
    }

    @POST
    @RolesAllowed("USER")
    public Response create(@Context SecurityContext context, @Valid EmployeeRequest.Create request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok().build();
    }

    @PUT
    @Path("/{productId}")
    @RolesAllowed("USER")
    public Response update(@Context SecurityContext context, @PathParam("productId") UUID productId, @Valid EmployeeRequest.Update request) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{productId}")
    @RolesAllowed("USER")
    public Response delete(@Context SecurityContext context, @PathParam("productId") UUID productId) {
        var subject = validateSubject(context.getUserPrincipal());
        return Response.noContent().build();
    }
}
