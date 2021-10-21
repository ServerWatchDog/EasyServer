package i.watch.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StringFillUtilsTest {

    @Test
    fun fill() {

        assertEquals(
            StringFillUtils.fill(
                "Hello,#{user.name}",
                mapOf("user.name" to "dragon")
            ),
            "Hello,dragon"
        )
        assertEquals(
            StringFillUtils.fill(
                "Hello,#{user.dir}",
                mapOf("user.name" to "dragon")
            ),
            "Hello,#{user.dir}"
        )
    }
}
