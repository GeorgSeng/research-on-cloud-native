package at.htl.dcnq.coffee_shop.control

import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import at.htl.dcnq.coffeeshop.entity.OrderStatus
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class OrderProcessor {

    @Inject
    @field: Default
    lateinit var barista: Barista

    //@Traced
    fun processOrder(order: CoffeeOrder) {
        val status: OrderStatus = barista.retrieveBrewStatus(order)
        order.orderStatus = status
    }
}

