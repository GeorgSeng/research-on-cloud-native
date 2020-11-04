package at.htl.dcnq.coffeeshop.entity

import java.util.function.Predicate
import java.util.stream.Stream

enum class OrderStatus {
    PREPARING, FINISHED, COLLECTED, UNKNOWN;

    companion object {
        fun fromString(string: String?): OrderStatus {
            return Stream.of(*at.htl.dcnq.coffeeshop.entity.OrderStatus.values())
                    .filter(Predicate { t: OrderStatus -> t.name.equals(string, ignoreCase = true) })
                    .findAny().orElse(null)
        }
    }
}