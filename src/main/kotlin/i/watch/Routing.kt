package i.watch

import i.watch.config.global.DataBaseConfig.connect
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import kotlin.math.abs
import kotlin.random.Random

object Users : Table<Nothing>("t_users") {
    val id = int("s_id").primaryKey()
    val name = varchar("name")
}

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
            connect.useTransaction {
                for (queryRowSet in connect.from(Users).select()) {
                    log.info("name:{},id:{}", queryRowSet[Users.name], queryRowSet[Users.id])
                }
                connect.insert(Users) {
                    set(it.name, "Id")
                }
                val random = abs(Random.nextInt())
                println(random)
                if (random % 3 == 0) {
                    log.info("ROLL")
                    it.rollback()
                } else {
                    log.info("COMMENT")
                }
            }
        }
    }
}
