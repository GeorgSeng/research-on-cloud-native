package at.htl.dcnq.coffee_shop.control

import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import at.htl.dcnq.coffeeshop.entity.OrderStatus
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.enterprise.context.ApplicationScoped
import javax.json.Json
import javax.json.JsonObject
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

@ApplicationScoped
class   Barista {

    private lateinit var client: Client
    private lateinit var target: WebTarget

    @PostConstruct
    private fun initClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()
        target = client.target("http://barista:9080/barista/resources/brews/{id}")
    }

    fun brewCoffee(order: CoffeeOrder): OrderStatus {
        val requestBody = createRequestBody(order)
        val response = sendBrewRequest(requestBody, order.id.toString())
        return readStatus(response)
    }

    private fun createRequestBody(order: CoffeeOrder): JsonObject {
        return Json.createObjectBuilder()
                .add("type", order.type.name.toLowerCase())
                .build()
    }

    private fun sendBrewRequest(requestBody: JsonObject, id: String): Response {
        return try {
            target.resolveTemplate("id", id)
                    .request()
                    .put(Entity.json(requestBody))
        } catch (e: Exception) {
            throw IllegalStateException("Could not place brew request, reason: " + e.message, e)
        }
    }

//    @Retry
//    @Fallback(fallbackMethod = "unknownBrewStatus")
    fun retrieveBrewStatus(order: CoffeeOrder): OrderStatus {
        val response = getBrewStatus(order.id.toString())
        return readStatus(response)
    }

    private fun unknownBrewStatus(order: CoffeeOrder): OrderStatus {
        return OrderStatus.UNKNOWN
    }

    private fun getBrewStatus(id: String): Response {
        return target.resolveTemplate("id", id)
                .request().get()
    }

    private fun readStatus(response: Response): OrderStatus {
        check(response.statusInfo.family == Response.Status.Family.SUCCESSFUL) { "Could not retrieve brew status, response: " + response.status }

        val entity = response.readEntity(JsonObject::class.java)

        return OrderStatus.fromString(entity.getString("status", null))
                ?: throw RuntimeException("Could not read known status from response: $entity")
    }

    @PreDestroy
    private fun closeClient() {
        client.close()
    }
}