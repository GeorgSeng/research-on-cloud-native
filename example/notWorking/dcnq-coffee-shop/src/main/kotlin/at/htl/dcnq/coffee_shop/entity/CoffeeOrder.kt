package at.htl.dcnq.coffeeshop.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.json.bind.annotation.JsonbProperty
import javax.json.bind.annotation.JsonbTransient
import javax.json.bind.annotation.JsonbTypeAdapter
import javax.persistence.*

@Entity
@Table(name = "orders")
//The CoffeeOrder, which represents the entities of our domain
class CoffeeOrder {

    @Id
    lateinit var id: String

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "coffee_type")
    lateinit var type: CoffeeType

    @JsonbProperty("status")
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    lateinit var orderStatus: OrderStatus

    override fun toString(): String {
        return "CoffeeOrder{" +
                "id=" + id +
                ", type=" + type +
                ", orderStatus=" + orderStatus +
                '}'
    }
}
