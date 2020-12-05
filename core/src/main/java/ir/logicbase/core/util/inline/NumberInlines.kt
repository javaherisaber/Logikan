package ir.logicbase.core.util.inline

/**
 * invoke [callback] when [input] is not null or zero
 */
inline fun <T: Number, R> whenNotNullOrZero(input: T?, callback: (T) -> R): R? {
    return if (input != null && input.toDouble() != 0.0) {
        callback.invoke(input)
    } else null
}

inline fun <T: Number, R> whenNullOrZero(input: T?, callback: (T?) -> R): R? {
    return if (input == null || input.toDouble() == 0.0) {
        callback.invoke(input)
    } else null
}