package ua.alegator1209.feature_repositories.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

internal class LanguageBarView : View {
    private val paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr)

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        paint.strokeWidth = height.toFloat()

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.BLUE
        canvas.drawLine(0F, 0F, width.toFloat(), height.toFloat(), paint)
    }
}
