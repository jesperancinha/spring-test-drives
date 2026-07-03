package org.jesperancinha.smtd.mixservice.configuration

import org.jesperancinha.smtd.mixservice.domain.InventoryService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
class InventoryProcessor(
    private val managementInventoryService: InventoryService,
    private val teamInventoryService: InventoryService,
    private val customerInventoryService: InventoryService
){
    fun performAll() {
        managementInventoryService.performOperation()
        teamInventoryService.performOperation()
        customerInventoryService.performOperation()
    }
}
@Configuration
class InventoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "inventory.management")
    fun managementInventoryService(): InventoryService {
        return InventoryService()
    }

    @Bean
    @ConfigurationProperties(prefix = "inventory.team")
    fun teamInventoryService(): InventoryService {
        return InventoryService()
    }

    @Bean
    @ConfigurationProperties(prefix = "inventory.customer")
    fun customerInventoryService(): InventoryService {
        return InventoryService()
    }
}