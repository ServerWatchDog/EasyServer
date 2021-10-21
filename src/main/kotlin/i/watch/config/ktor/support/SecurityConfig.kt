package i.watch.config.ktor.support

import i.watch.config.ktor.IKtorConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

object SecurityConfig : IKtorConfig {
    override fun Application.config(devMode: Boolean) {
        install(CORS) {
            if (devMode) {
                method(HttpMethod.Options)
                method(HttpMethod.Put)
                method(HttpMethod.Delete)
                method(HttpMethod.Patch)
                header(HttpHeaders.Authorization)
                allowCredentials = true
                anyHost()
            } else {
                log.info("{}", "PO")
            }
        }
    }
}
