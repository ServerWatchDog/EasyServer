package i.watch.utils

import java.util.regex.Pattern

/**
 * 字符串占位符替换方案
 */
object StringFillUtils {
    private val regex = Regex("#\\{.+?}")
    private val spit = Regex("(^#\\{|}$)")
    private val pattern = Pattern.compile(regex.pattern)

    /**
     * 将原始字符串下所有符合规则的占位符替换
     *
     * @param source String 原始字符串
     * @param fillItems Map<String, Any> 规则
     * @return String 替换后的数据
     */
    fun fill(source: String, fillItems: Map<String, Any>): String {
        val container = StringBuilder()
        container.append(source)
        val keySkipItem = HashSet<String>()
        val matcher = pattern.matcher(container.toString())
        while (matcher.find()) {
            val data = matcher.group()
            val key = data.split(spit)[1]
            if (keySkipItem.contains(key)) {
                continue
                // 出现相同字段，自动剔除，防止无限循环
            }
            val propData = fillItems[key]
            if (propData != null)
                container.replace(0, container.length, container.toString().replace(data, propData.toString()))
            keySkipItem.add(key)
        }
        keySkipItem.clear()
        return container.toString()
    }
}
