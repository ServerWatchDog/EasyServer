package i.watch.utils

import java.util.Optional
import kotlin.system.exitProcess

fun <T : Any> Optional<T>.getOrShutdown(key: String): T {
    return if (this.isEmpty) {
        System.err.println("致命错误：程序运行需要配置 '$key' !")
        exitProcess(-1)
    } else {
        this.get()
    }
}
