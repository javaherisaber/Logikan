package ir.logicbase.core.ui.util.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.resources.TextAppearanceConfig

/**
 * Synchronize font while inflating view in a view group (eg. in chip group)
 */
inline fun <T> View.inflateViewWithSynchronizedFont(action: (LayoutInflater) -> T): T {
    TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    val result = action(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    TextAppearanceConfig.setShouldLoadFontSynchronously(false)
    return result
}