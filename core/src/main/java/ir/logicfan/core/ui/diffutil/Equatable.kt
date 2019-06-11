package ir.logicfan.core.ui.diffutil

/**
 * To be used by generic DiffUtil classes in areContentTheSame function
 */
interface Equatable {
    override fun equals(other: Any?): Boolean
}