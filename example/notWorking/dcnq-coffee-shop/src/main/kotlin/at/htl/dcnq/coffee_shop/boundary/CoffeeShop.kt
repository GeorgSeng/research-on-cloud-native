package at.htl.dcnq.coffee_shop.boundary

import at.htl.dcnq.coffee_shop.control.Barista
import at.htl.dcnq.coffee_shop.control.OrderProcessor
import at.htl.dcnq.coffee_shop.repository.CoffeeOrderRepository
import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import at.htl.dcnq.coffeeshop.entity.CoffeeType
import at.htl.dcnq.coffeeshop.entity.OrderStatus
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.*
import javax.ejb.Stateless
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.ws.rs.core.Response

@Stateless
@ApplicationScoped
// Domain Class
class CoffeeShop {
    @Inject
    @field: Default
    lateinit var barista: Barista

    @Inject
    @field: Default
    lateinit var orderProcessor: OrderProcessor

    @Inject
    @field: Default
    lateinit var coffeeOrderRepo: CoffeeOrderRepository

//    @PersistenceContext
//    var entityManager: EntityManager? = null

    @Inject
    @ConfigProperty(name = "coffeeShop.order.defaultCoffeeType", defaultValue = "ESPRESSO")
    private lateinit var defaultCoffeeType: CoffeeType


    fun getOrders(): List<CoffeeOrder> {
        return coffeeOrderRepo.listAll()
    }

    fun getOrder(id: UUID): CoffeeOrder {
        return coffeeOrderRepo.findById(id.toString().toLong())!!
    }

    fun orderCoffee(order: CoffeeOrder): CoffeeOrder {
        order.id = UUID.randomUUID().toString()
        setDefaultType(order)
        val status: OrderStatus = barista.brewCoffee(order)
        order.orderStatus = status
        coffeeOrderRepo.persist(order)

        return order
    }

    private fun setDefaultType(order: CoffeeOrder) {
        if (order.type == null) order.type = defaultCoffeeType
    }

    fun processUnfinishedOrders() {
        coffeeOrderRepo.findUnfinished().forEach(orderProcessor::processOrder)
    }
}