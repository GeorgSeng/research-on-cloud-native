package at.htl.dcnq.coffee_shop.boundary

import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import java.net.URI
import java.util.*
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OrdersResource {

    @Inject
    @field: Default
    lateinit var coffeeShop: CoffeeShop

    @Context
    lateinit var uriInfo: UriInfo

    @GET
    public fun getOrders(): List<CoffeeOrder>{return coffeeShop.getOrders()}

    private fun buildOrder(order: CoffeeOrder): JsonObject {
        return Json.createObjectBuilder()
                .add("type", order.type.name)
                .add("status", order.orderStatus.name)
                .add("_self", buildUri(order).toString())
                .build()
    }

    fun emptyOrders(): JsonArray {
        return Json.createArrayBuilder().build()
    }

    @GET
    @Path("{id}")
//    @Operation(summary = "Get a coffee order", description = "Returns a CoffeeOrder object for the given order id.")
    fun getOrder(@PathParam("id") id: UUID?): CoffeeOrder {
        return coffeeShop.getOrder(id!!)
    }

    @POST
//    @Timed(name = "orderCoffee.timer", displayName = "Timings to Coffee Orders", description = "Time taken to place a new coffee order.")
    fun orderCoffee(order: @Valid @NotNull CoffeeOrder?): Response {
        val storedOrder: CoffeeOrder = coffeeShop.orderCoffee(order!!)
        return Response.created(buildUri(storedOrder)).build()
    }

    private fun buildUri(order: CoffeeOrder): URI {
        return uriInfo.requestUriBuilder
                .path(OrdersResource::class.java)
                .path(OrdersResource::class.java, "getOrder")
                .build(order.id)
    }
}
