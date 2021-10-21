package i.watch.config.global

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import i.watch.utils.Config
import i.watch.utils.getOrShutdown
import org.ktorm.database.Database

/**
 * 数据库配置
 */
object DataBaseConfig {

    val dataSource by lazy {
        val config = HikariConfig()
        config.jdbcUrl = Config.getString("datasource.url").getOrShutdown("datasource.url")
        config.username = Config.getString("datasource.username").getOrShutdown("datasource.username")
        config.password = Config.getString("datasource.password").getOrShutdown("datasource.password")
        HikariDataSource(config)
    }
    val connect by lazy {
        Database.connect(dataSource)
    }
}
