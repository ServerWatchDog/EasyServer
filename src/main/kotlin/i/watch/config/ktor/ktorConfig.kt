package i.watch.config.ktor

import i.watch.config.ktor.support.CacheConfig
import i.watch.config.ktor.support.SecurityConfig
import i.watch.config.ktor.support.SerializationConfig
import i.watch.config.ktor.support.WebSocketConfig
import io.ktor.application.Application

/**
 * 配置项目依赖支持
 */
fun Application.loadPlugins() {
    val developMode = System.getProperty("io.ktor.development", "false").uppercase() == "TRUE"
    listOf(
        CacheConfig,
        SecurityConfig,
        SerializationConfig,
        WebSocketConfig
    ).forEach { it.config0(this, developMode) }
}
