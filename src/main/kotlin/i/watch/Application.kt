package i.watch

import i.watch.config.ktor.loadPlugins
import i.watch.utils.Config
import i.watch.utils.ConfigLoader
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        ConfigLoader.sayHelloWorld()
        embeddedServer(
            Netty, port = Config.getInt("global.server.port").orElse(8080),
            host = Config.getString("global.server.ip-address").orElse("127.0.0.1"),
            watchPaths = listOf("classes", "resources")

        ) {
            loadPlugins() // 加载配置插件
            configureRouting() // 加载路由
        }.start(wait = true)
    }
}
