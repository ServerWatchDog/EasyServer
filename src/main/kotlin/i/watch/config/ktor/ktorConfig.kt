package i.watch.config.ktor

import i.watch.config.ktor.support.CacheConfig
import i.watch.config.ktor.support.FlywayConfig
import i.watch.config.ktor.support.SecurityConfig
import i.watch.config.ktor.support.SerializationConfig
import i.watch.config.ktor.support.WebSocketConfig
import i.watch.utils.Config
import io.ktor.application.Application

/**
 * 配置项目依赖支持
 */
fun Application.loadPlugins() {
    val developMode = Config.getBoolean("development")
        .orElseGet { throw RuntimeException("配置文件加载出现问题！") }
    listOf(
        CacheConfig,
        SecurityConfig,
        SerializationConfig,
        WebSocketConfig,
        FlywayConfig
    ).forEach { it.config0(this, developMode) }
}
