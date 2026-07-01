package org.jesperancinha.smtd.mixservice.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

data class AppData(
    val version: String
)

@ConfigurationProperties(prefix = "app")
data class AppProperties(
    val name: String,
    val data: AppData
) {
    @ConstructorBinding
    constructor(name: String, version:String) : this(name, AppData(version))
}