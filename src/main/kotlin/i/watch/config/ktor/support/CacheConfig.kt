package i.watch.config.ktor.support

import i.watch.config.ktor.IKtorConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CachingHeaders
import io.ktor.http.CacheControl
import io.ktor.http.ContentType
import io.ktor.http.content.CachingOptions

object CacheConfig : IKtorConfig {

    override fun Application.config(devMode: Boolean) {
        install(CachingHeaders) {
            options { outgoingContent ->
                when (outgoingContent.contentType?.withoutParameters()) {
                    ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
                    else -> null
                }
            }
        }
    }
}
