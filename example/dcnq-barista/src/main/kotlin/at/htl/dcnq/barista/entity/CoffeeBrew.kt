package at.htl.dcnq.barista.entity

import javax.enterprise.context.ApplicationScoped


class CoffeeBrew {

    private var status = "PREPARING"
    private var type: String? = null

    constructor() {}

    constructor(type: String?) {
        this.type = type
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

}
