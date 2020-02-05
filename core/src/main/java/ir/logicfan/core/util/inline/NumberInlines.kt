package ir.logicfan.core.util.inline

/**
 * invoke [callback] when [input] is not null or zero
 */
inline fun <T, R> whenNotNullOrZero(input: T?, callback: (T) -> R): R? {
    return if (input != null && input != 0) {
        callback.invoke(input)
    } else null
}