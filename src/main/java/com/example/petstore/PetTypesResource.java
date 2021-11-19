package com.example.petstore;
//
//public class petTypes {
//}


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pet-types")
@Produces("application/json")
public class PetTypesResource {
    List<String> petTypes = new ArrayList<String>();

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    public Response getPetTypes() {

        return Response.ok(petTypes).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @GET
    @Path("{petTypeId}")
    public Response getPetType(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId <= 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(petTypes.get(petTypeId-1)).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "Pet Add failed") })
    @POST
    @Path("/addPetType")
    public Response addPetType( @FormParam("petTypeName" ) String petTypeName) {

        petTypes.add(petTypeName);

        return Response.ok(petTypes).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "Pet Add failed") })
    @PUT
    @Path("/updatePetType")
    public Response updatePetType(@FormParam("petTypeId" ) int petTypeId, @FormParam("petTypeName" ) String petTypeName) {


        petTypes.set(petTypeId-1,petTypeName);
        return Response.ok(petTypes).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "Pet Add failed") })
    @DELETE
    @Path("/deletePetType")
    public Response deletePetType(@FormParam("petTypeId" ) int petTypeId) {


        petTypes.remove(petTypeId-1);
        return Response.ok(petTypes).build();

    }
}
