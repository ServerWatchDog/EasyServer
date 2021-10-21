package i.watch.config.ktor.support

import i.watch.config.ktor.IKtorConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json

object SerializationConfig : IKtorConfig {
    override fun Application.config(devMode: Boolean) {
        install(ContentNegotiation) {
            json()
        }
    }
}
