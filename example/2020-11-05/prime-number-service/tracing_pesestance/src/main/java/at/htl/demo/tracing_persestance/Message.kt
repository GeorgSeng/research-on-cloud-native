package at.htl.demo.tracing_persestance;

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
class Message : PanacheEntity() {

    lateinit var message: String;
}