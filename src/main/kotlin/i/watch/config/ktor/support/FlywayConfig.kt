package i.watch.config.ktor.support

import com.viartemev.ktor.flyway.FlywayFeature
import i.watch.config.global.DataBaseConfig
import i.watch.config.ktor.IKtorConfig
import io.ktor.application.Application
import io.ktor.application.install

object FlywayConfig : IKtorConfig {
    override fun Application.config(devMode: Boolean) {
        this.environment
        install(FlywayFeature) {
            dataSource = DataBaseConfig.dataSource
            locations = arrayOf("db")
        }
    }
}
