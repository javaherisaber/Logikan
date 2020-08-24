package ir.logicfan.core.ui.databinding.adapter

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 * Define listener for EditText keyboard actions
 */
@BindingAdapter(value = ["onActionDone", "onActionGo", "onActionSend", "onActionSearch", "onActionNext"], requireAll = false)
fun EditText.setOnActionDoneListener(
    onActionDoneListener: OnEditTextActionDoneListener?,
    onActionGoListener: OnEditTextActionGoListener?,
    onActionSendListener: OnEditTextActionSendListener?,
    onActionSearchListener: OnEditTextActionSearchListener?,
    onActionNextListener: OnEditTextActionNextListener?
) {
    this.setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> onActionDoneListener?.onActionDone()
            EditorInfo.IME_ACTION_GO -> onActionGoListener?.onActionGo()
            EditorInfo.IME_ACTION_SEND -> onActionSendListener?.onActionSend()
            EditorInfo.IME_ACTION_SEARCH -> onActionSearchListener?.onActionSearch()
            EditorInfo.IME_ACTION_NEXT -> onActionNextListener?.onActionNext()
        }
        false
    }
}

interface OnEditTextActionDoneListener {
    fun onActionDone()
}

interface OnEditTextActionGoListener {
    fun onActionGo()
}

interface OnEditTextActionSendListener {
    fun onActionSend()
}

interface OnEditTextActionSearchListener {
    fun onActionSearch()
}

interface OnEditTextActionNextListener {
    fun onActionNext()
}