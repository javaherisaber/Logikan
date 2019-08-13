package ir.logicfan.core.ui.widget.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.RequiresApi
import ir.logicfan.core.R

class CoreColoredStrikeThroughTextView : TextView {

    private lateinit var paint: Paint

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context, attrs, defStyleAttr, defStyleRes
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs == null) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoreColoredStrikeThroughTextView)
            val strikeThroughColor =
                typedArray.getColor(R.styleable.CoreColoredStrikeThroughTextView_core_strikeThroughColor, Color.GRAY)
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
        canvas?.drawLine(0F, height / 2F, width.toFloat(), height / 2F, paint)
    }
}