@file:Suppress("MemberVisibilityCanBePrivate")

package ir.logicbase.core.ui.widget.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ir.logicbase.core.R

class CoreColoredStrikeThroughTextView @JvmOverloads constructor (
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    private lateinit var paint: Paint
    var lineEnabled = true
        set(value) {
            field = value
            invalidate()
        }

    init {
        if (attrs == null) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoreColoredStrikeThroughTextView)
            val strikeThroughColor =
                typedArray.getColor(R.styleable.CoreColoredStrikeThroughTextView_core_strikeThroughColor, Color.GRAY)
            lineEnabled = typedArray.getBoolean(R.styleable.CoreColoredStrikeThroughTextView_core_lineEnabled, true)
            paint = Paint()
            paint.color = strikeThroughColor
            paint.strokeWidth = resources.displayMetrics.density
            paint.isStrikeThruText = true
            paint.style = Paint.Style.FILL
            paint.flags = Paint.ANTI_ALIAS_FLAG
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (lineEnabled) {
            canvas?.drawLine(0F, height / 2F, width.toFloat(), height / 2F, paint)
        }
    }
}