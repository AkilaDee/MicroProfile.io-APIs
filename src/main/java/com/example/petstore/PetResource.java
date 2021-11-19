package com.example.petstore;

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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {
	List<Pet> pets = new ArrayList<Pet>();

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {

		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(pets.get(petId-1)).build();
		
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet Add failed") })
	@POST
	@Path("/addPet")
	public Response addPet(@FormParam("petAge" ) int petAge, @FormParam("petName" ) String petName, @FormParam("petType" ) String petType) {

		Pet pet = new Pet();
		pet.setPetId(pets.size()+1);
		pet.setPetAge(petAge);
		pet.setPetName(petName);
		pet.setPetType(petType);

		pets.add(pet);

		return Response.ok(pet).build();

	}
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet Add failed") })
	@PUT
	@Path("/updatePet")
	public Response updatePet(@FormParam("petId" ) int petId, @FormParam("petAge" ) int petAge,@FormParam("petName" ) String petName, @FormParam("petType" ) String petType) {


		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(petAge);
		pet.setPetName(petName);
		pet.setPetType(petType);

		pets.set(petId-1,pet);
		return Response.ok(pet).build();

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Added successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet Add failed") })
	@DELETE
	@Path("/deletePet")
	public Response deletePet(@FormParam("petId" ) int petId) {


		pets.remove(petId-1);
		return Response.ok(pets).build();

	}
}
