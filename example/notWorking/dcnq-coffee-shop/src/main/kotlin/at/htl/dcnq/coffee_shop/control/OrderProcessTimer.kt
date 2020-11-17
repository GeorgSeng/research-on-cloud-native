package at.htl.dcnq.coffee_shop.control

import at.htl.dcnq.coffee_shop.boundary.CoffeeShop
import javax.ejb.Schedule
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.inject.Inject

@Singleton
@Startup
class OrderProcessTimer {
    @Inject
    lateinit var coffeeShop: CoffeeShop

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    fun processOrder() {
        coffeeShop.processUnfinishedOrders()
    }
}