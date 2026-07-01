package org.jesperancinha.smtd.mixservice.domain

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "inventory2.management")
data class Test(
    val url: String,
    val timeout: Long
)

class InventoryService {
    var url: String? = null
    var timeout: Long? = null

    fun performOperation() {
        println("Connecting to $url with a timeout of $timeout ms")
    }

    override fun toString(): String {
        return "InventoryService(url='$url', timeout=$timeout)"
    }
}
