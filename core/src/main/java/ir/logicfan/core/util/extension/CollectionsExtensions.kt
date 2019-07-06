package ir.logicfan.core.util.extension

/**
 * Generate comma separated values from your Collection of integer
 * eg. 1,3,5,7,8
 */
fun List<Int>.createCommaSeparatedValues(): String = this.joinToString(separator = ",") { it.toString() }
fun Set<Int>.createCommaSeparatedValues(): String = this.joinToString(separator = ",") { it.toString() }