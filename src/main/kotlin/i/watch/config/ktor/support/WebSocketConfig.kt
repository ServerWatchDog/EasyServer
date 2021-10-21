package i.watch.config.ktor.support

import i.watch.config.ktor.IKtorConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.http.cio.websocket.pingPeriod
import io.ktor.http.cio.websocket.timeout
import io.ktor.websocket.WebSockets
import java.time.Duration

object WebSocketConfig : IKtorConfig {
    override fun Application.config(devMode: Boolean) {
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(15)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
    }
}
