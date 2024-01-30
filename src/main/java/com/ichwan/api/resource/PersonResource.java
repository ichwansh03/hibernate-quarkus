package com.ichwan.api.resource;

import com.ichwan.api.dto.PersonRequest;
import com.ichwan.api.service.PersonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("api/v1/persons")
@Tag(name = "Person Resource", description = "REST API with Quarkus")
public class PersonResource {

    @Inject
    PersonService personService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Create Movie",
        description = "create movie by request from record class"
    )
    @APIResponse(
        responseCode = "200",
        description = "Data has been created",
        content = @Content(
            mediaType = "application/json"
        )
    )
    public Response create(PersonRequest person) {
        personService.create(person);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Operation(
            summary = "Get Movie By Id",
            description = "get movie by ID param"
    )
    @APIResponse(
            responseCode = "200",
            description = "Data successfully viewed",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(personService.getById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get All Movie",
            description = "get all movies with stream API"
    )
    @APIResponse(
            responseCode = "200",
            description = "Data's successfully viewed",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    public Response getAll() {
        return Response.ok(personService.getAll()).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Operation(
            summary = "Update Movie",
            description = "update movie by request from record class and id param"
    )
    @APIResponse(
            responseCode = "200",
            description = "Data has been updated",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    public Response update(@PathParam("id") Long id, PersonRequest person) {
        personService.update(id, person);
        return Response.ok().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Operation(
            summary = "Delete Movie",
            description = "delete movie by id"
    )
    @APIResponse(
            responseCode = "200",
            description = "Data has been deleted",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    public Response delete(@PathParam("id") Long id) {
        personService.delete(id);
        return Response.ok().build();
    }
}
