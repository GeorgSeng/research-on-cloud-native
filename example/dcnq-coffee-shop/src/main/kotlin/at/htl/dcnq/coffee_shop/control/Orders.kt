package at.htl.dcnq.coffee_shop.control

import at.htl.dcnq.coffee_shop.repository.CoffeeOrderRepository
import at.htl.dcnq.coffeeshop.entity.CoffeeOrder
import java.util.*
import java.util.Map
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class Orders {

    @Inject
    @field: Default
    lateinit var coffeeOrderRepo: CoffeeOrderRepository

    private val orders: ConcurrentHashMap<UUID, CoffeeOrder> = ConcurrentHashMap<UUID, CoffeeOrder>()

    fun retrieveAll(): List<CoffeeOrder> {
        return coffeeOrderRepo.listAll()
    }

    fun retrieve(id: UUID?): CoffeeOrder? {
        return orders[id]
    }

    fun store(id: UUID, order: CoffeeOrder) {
        orders[id] = order
    }

    val unfinishedOrders: List<CoffeeOrder>
        get() = coffeeOrderRepo.findUnfinished()
}