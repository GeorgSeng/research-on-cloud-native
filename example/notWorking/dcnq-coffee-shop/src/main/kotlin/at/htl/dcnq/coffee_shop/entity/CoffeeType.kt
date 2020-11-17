package at.htl.dcnq.coffeeshop.entity

import java.util.function.Predicate
import java.util.stream.Stream

enum class CoffeeType {
    ESPRESSO, LATTE, POUR_OVER;

    companion object {
        fun fromString(string: String?): CoffeeType {
            return Stream.of(*at.htl.dcnq.coffeeshop.entity.CoffeeType.values())
                    .filter(Predicate { t: CoffeeType -> t.name.equals(string, ignoreCase = true) })
                    .findAny().orElse(null)
        }
    }
}