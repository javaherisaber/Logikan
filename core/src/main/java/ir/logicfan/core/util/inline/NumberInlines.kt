package ir.logicfan.core.util.inline

/**
 * invoke [callback] when [input] is not null or zero
 */
inline fun <T: Number, R> whenNotNullOrZero(input: T?, callback: (T) -> R): R? {
    return if (input != null && input.toDouble() != 0.0) {
        callback.invoke(input)
    } else null
}

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}