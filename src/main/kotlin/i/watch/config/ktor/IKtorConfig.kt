package i.watch.config.ktor

import io.ktor.application.Application

/**
 * 配置统一化管理
 */
interface IKtorConfig {
    fun Application.config(devMode: Boolean)
    fun config0(application: Application, devMode: Boolean) {
        application.config(devMode)
    }
}
