package ir.logicfan.core.ui.databinding

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object CustomBindingAdapters {

    /**
     * hide the view if number is zero
     */
    @BindingAdapter("hideIfZero")
    @JvmStatic
    fun hideIfZero(view: View, number: Int) {
        view.visibility = if (number == 0) View.GONE else View.VISIBLE
    }

    /**
     *  Sets the value of the progress bar so that 5 likes will fill it up.
     *
     *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     *  whenever any of the attribute changes.
     */
    @BindingAdapter(value = ["progressScaled", "android:max"], requireAll = true)
    @JvmStatic
    fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
        progressBar.progress = (likes * max / 5).coerceAtMost(max)
    }
}