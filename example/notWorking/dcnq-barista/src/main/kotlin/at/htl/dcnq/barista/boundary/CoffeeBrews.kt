package at.htl.dcnq.barista.boundary

import at.htl.dcnq.barista.control.RandomStatusProcessor
import at.htl.dcnq.barista.entity.CoffeeBrew
import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class CoffeeBrews {
    private val coffeeBrews: MutableMap<String, CoffeeBrew> = ConcurrentHashMap<String, CoffeeBrew>()

    @Inject
    @field: Default
    lateinit var statusProcessor: RandomStatusProcessor

    fun startBrew(id: String, coffeeType: String): CoffeeBrew? {
        println("starting to brew: $coffeeType")
        val brew = CoffeeBrew(coffeeType)
        coffeeBrews[id] = brew
        return brew
    }

    fun retrieveBrew(id: String): CoffeeBrew? {
        println("retrieving brew: $id")
        val brew: CoffeeBrew = coffeeBrews[id] ?: return null
        brew.setStatus(statusProcessor!!.processStatus(brew)!!)
        return brew
    }

    fun retrieveBrews(): Map<String, CoffeeBrew>? {
        return coffeeBrews
    }
}
