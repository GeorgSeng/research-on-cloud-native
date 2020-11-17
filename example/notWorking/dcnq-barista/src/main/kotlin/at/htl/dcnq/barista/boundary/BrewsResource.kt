package at.htl.dcnq.barista.boundary

import at.htl.dcnq.barista.entity.CoffeeBrew
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.json.Json
import javax.json.JsonObject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/brews")
public class BrewsResource{

    @Inject
    @field: Default
    lateinit var coffeeBrews: CoffeeBrews

    @GET
    @Path("/{id}")
    fun retrieveCoffeeBrew(@PathParam("id") id: String?): Response? {
        val brew: CoffeeBrew = coffeeBrews.retrieveBrew(id!!)
                ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(buildResponse(brew)).build()
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun retrieveCoffeeBrews(): Response? {
        val brews = coffeeBrews.retrieveBrews()
        return Response.ok(brews).build()
    }

    @PUT
    @Path("/{id}")
    fun updateCoffeeBrew(@PathParam("id") id: String?, jsonObject: JsonObject): Response? {
        val coffeeType = jsonObject.getString("type", null) ?: throw BadRequestException()
        val brew = coffeeBrews.startBrew(id!!, coffeeType)
        return Response.status(Response.Status.CREATED)
                .entity(buildResponse(brew!!))
                .build()
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "hello"

    private fun buildResponse(brew: CoffeeBrew): JsonObject? {
        return Json.createObjectBuilder()
                .add("status", brew.getStatus())
                .add("type", brew.getType())
                .build()
    }
}