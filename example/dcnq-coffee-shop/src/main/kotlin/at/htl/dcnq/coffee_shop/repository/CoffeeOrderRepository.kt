package at.htl.dcnq.coffee_shop.repository

import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import at.htl.dcnq.coffeeshop.entity.OrderStatus
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CoffeeOrderRepository: PanacheRepository<CoffeeOrder> {
    fun findUnfinished() = list("status", OrderStatus.COLLECTED,
            OrderStatus.PREPARING,
            OrderStatus.UNKNOWN)
}

