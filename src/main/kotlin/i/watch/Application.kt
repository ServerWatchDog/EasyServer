package i.watch

import i.watch.config.ktor.loadPlugins
import i.watch.utils.ConfigLoader
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        ConfigLoader.sayHelloWorld()
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", watchPaths = listOf("classes")) {
            loadPlugins()
        }.start(wait = true)
    }
}
