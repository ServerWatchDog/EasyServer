package i.watch.utils

import com.github.openEdgn.logger4k.getLogger
import java.util.Properties

/**
 *  简易的配置文件加载器
 */
object ConfigLoader {
    val config: Map<String, String>
    private val logger = getLogger()

    init {
        val config = HashMap<String, String>()
        this.config = config
        tryLoadClassPath(config, "/bootstrap.properties") // 加载预定义配置
        tryLoadClassPath(config, "/application.properties") // 加载默认
        val profile = System.getProperty(
            "global.profile",
            config.getOrDefault("global.profile", "prod")
        ) // 取项目配置
        logger.info("Using profile : {}.", profile)
        tryLoadClassPath(config, "/application-$profile.properties")
        for (mutableEntry in config.iterator()) {
            val systemProp = System.getProperty(mutableEntry.key)
            if (systemProp != null) {
                mutableEntry.setValue(systemProp)
            } // 取环境变量配置覆盖配置文件配置
        }
        val systemProperties = System.getProperties()
        config.forEach { (t, _) ->
            systemProperties.putIfAbsent(t, Config.getString(t).get())
        } // 注入用户配置到系统
    }

    fun sayHelloWorld() {
        if (Config.getBoolean("global.show-banner").orElse(true)) {
            try {
                println(
                    javaClass.getResourceAsStream("/banner.txt")!!
                        .bufferedReader().readText()
                )
            } catch (e: Exception) {
            }
        }
    }

    private fun tryLoadClassPath(currentMap: MutableMap<String, String>, location: String) {
        tryLoad(currentMap) {
            val properties = Properties()
            properties.load(javaClass.getResourceAsStream(location)!!.bufferedReader())
            properties.map {
                it.key.toString() to it.value.toString()
            }.associate {
                Pair(it.first, it.second)
            }
        }
    }

    private fun tryLoad(
        currentMap: MutableMap<String, String>,
        loader: () -> Map<String, String>
    ) {
        try {
            loader()
        } catch (e: Throwable) {
            logger.debugThrowable("Properties Load Fail!", e)
            emptyMap()
        }.forEach { (t, u) ->
            currentMap[t] = u
        }
    }
}
