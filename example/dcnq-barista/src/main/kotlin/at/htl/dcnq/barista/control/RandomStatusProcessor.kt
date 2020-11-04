package at.htl.dcnq.barista.control

import at.htl.dcnq.barista.entity.CoffeeBrew
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
public class RandomStatusProcessor {
    private val PERCENTAGE = 0.4

    fun processStatus(brew: CoffeeBrew): String? {
        val status: String = brew.getStatus()!! // bad
        return if (randomSelection()) updateStatus(brew) else status
    }

    private fun randomSelection(): Boolean {
        return Random().nextDouble() <= PERCENTAGE
    }

    private fun updateStatus(brew: CoffeeBrew): String? {
        return when (brew.getStatus()) {
            "PREPARING" -> "FINISHED"
            "FINISHED" -> "COLLECTED"
            "COLLECTED" -> "COLLECTED"
            else -> throw IllegalArgumentException("Unknown status " + brew.getStatus())
        }
    }
}