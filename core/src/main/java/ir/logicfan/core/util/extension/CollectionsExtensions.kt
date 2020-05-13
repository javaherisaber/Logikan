package ir.logicfan.core.util.extension

/**
 * Generate comma separated values from your Collection of integer
 * eg. 1,3,5,7,8
 */
fun <T> Collection<T>.createCommaSeparatedValues(): String = this.joinToString(separator = ",") { it.toString() }

fun <T : Any> Collection<T?>.whenAllNotNull(block: () -> Unit) {
    if (this.all { it != null }) {
        block()
    }
}