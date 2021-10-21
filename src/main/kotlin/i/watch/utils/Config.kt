package i.watch.utils

import org.slf4j.LoggerFactory
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

/**
 * 全局配置加载器
 */
object Config {
    private val configCache = ConcurrentHashMap<String, String>()
    private val userConfig = ConfigLoader.config

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getInt(key: String): Optional<Int> {
        return getString(key).map {
            val data = it.toIntOrNull()
            if (data == null) {
                logger.warn("配置 '{}' 数据转换错误,无法将数据 '{}' 转换成 Int 类型.", key, it)
            }
            data
        }
    }

    fun getLong(key: String): Optional<Long> {
        return getString(key).map {
            val data = it.toLongOrNull()
            if (data == null) {
                logger.warn("配置 '{}' 数据转换错误,无法将数据 '{}' 转换成 Long 类型.", key, it)
            }
            data
        }
    }

    fun getBoolean(key: String): Optional<Boolean> {
        return getString(key).map {
            val data = it.toBooleanStrictOrNull()
            if (data == null) {
                logger.warn("配置 '{}' 数据转换错误,无法将数据 '{}' 转换成 Boolean 类型.", key, it)
            }
            data
        }
    }

    fun getString(key: String): Optional<String> {
        // 获取字符串并缓存 （如果存在）
        val cache = configCache[key] ?: try {
            val fill = StringFillUtils.fill(
                userConfig[key]!!,
                userConfig
            )
            configCache.putIfAbsent(key, fill) ?: fill
        } catch (e: Throwable) {
            null
        }
        return Optional.ofNullable(cache)
    }
}
