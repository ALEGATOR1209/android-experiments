package ua.alegator1209.feature_repositories.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.core.domain.model.Language
import java.util.*
import kotlin.math.max
import kotlin.math.min

private typealias LanguageColor = Int
private typealias StatLabel = String

internal class LanguageBarView : View {
    private var languages: SortedMap<Language, Pair<LanguageColor, StatLabel>> = sortedMapOf(
        comparator = { l1, l2 ->
            val byBytes = l2.byteCount.compareTo(l1.byteCount)
            if (byBytes == 0) l2.name.compareTo(l1.name) else byBytes
        }
    )

    private var totalBytes = 0L
    private val paint = Paint()
    private val textPaint = Paint()
    private val leftArcRect = RectF()
    private val rightArcRect = RectF()

    private var barHeight = 0
    private var stats = false
    private var statsPadding = 0
    private var statsEntryPadding = 0
    private var statsCircleRadius = 0
    private var statsTextColor = Color.BLACK
    private var statsTextSize = 0
    private var statsTypeface: Typeface? = null

    private val statEntrySize: Int
        get() = if (stats) {
            statsEntryPadding + max(statsTextSize, statsCircleRadius * 2)
        } else 0

    private val statsBlockSize: Int
        get() = if (stats) {
            statsPadding + statEntrySize * languages.size
        } else 0

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet) {
        readAttributes(context, attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr) {
        readAttributes(context, attributeSet, defStyleAttr)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        readAttributes(context, attributeSet, defStyleAttr, defStyleRes)
    }

    fun setLanguages(languages: List<Language>) {
        processLanguages(languages)
        requestLayout()
        invalidate()
    }

    private fun processLanguages(languages: List<Language>) {
        totalBytes = languages.sumOf { it.byteCount.toLong() }

        this.languages.clear()
        languages.associateWithTo(this.languages) { l ->
            context.colorFor(l) to languageLabel(l)
        }
    }

    private fun languageLabel(language: Language): String {
        val percent = language.byteCount.toFloat() / totalBytes * 100F
        return "%s - %.2f".format(language.name, percent) + "%"
    }

    private fun readAttributes(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.LanguageBarView,
            defStyleAttr, defStyleRes
        )

        with(typedArray) {
            val defaultLanguages = getBoolean(R.styleable.LanguageBarView_defaultLanguages, false)

            barHeight = getDimension(
                R.styleable.LanguageBarView_barHeight,
                barHeight.toFloat()
            ).toInt()

            stats = getBoolean(R.styleable.LanguageBarView_stats, stats)

            statsPadding = getDimension(
                R.styleable.LanguageBarView_statsPadding,
                statsPadding.toFloat()
            ).toInt()

            statsEntryPadding = getDimension(
                R.styleable.LanguageBarView_statsEntryPadding,
                statsEntryPadding.toFloat()
            ).toInt()

            statsCircleRadius = getDimension(
                R.styleable.LanguageBarView_statsCircleRadius,
                statsCircleRadius.toFloat()
            ).toInt()

            val statsTextAppearance = getResourceId(
                R.styleable.LanguageBarView_statsTextAppearance,
                0
            )

            readTextAppearance(statsTextAppearance)

            if (defaultLanguages) {
                loadDefaultLanguages()
            }
        }

        typedArray.recycle()
    }

    @SuppressLint("CustomViewStyleable")
    private fun readTextAppearance(styleId: Int) {
        val typedArray = context.obtainStyledAttributes(styleId, R.styleable.TextAppearance)

        with(typedArray) {
            statsTextColor = getColor(R.styleable.TextAppearance_android_textColor, statsTextColor)
            statsTextSize = getDimension(
                R.styleable.TextAppearance_android_textSize,
                statsTextSize.toFloat()
            ).toInt()

            val fontRes = getResourceId(R.styleable.TextAppearance_android_fontFamily, 0)
            try {
                statsTypeface = resources.getFont(fontRes)
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }
        }

        textPaint.apply {
            color = statsTextColor
            textSize = statsTextSize.toFloat()
            typeface = statsTypeface
        }

        typedArray.recycle()
    }

    private fun loadDefaultLanguages() {
        processLanguages(
            listOf(
                Language("Kotlin", 50),
                Language("Java", 20),
                Language("C#", 10),
                Language("Go", 15),
                Language("JavaScript", 5),
            )
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val heightWithStats = if (languages.isEmpty()) 0 else barHeight + statsBlockSize

        height = when (heightMode) {
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> min(height, heightWithStats)
            MeasureSpec.EXACTLY -> {
                if (height < heightWithStats) {
                    stats = false
                    barHeight
                } else {
                    height
                }
            }
            else -> heightWithStats
        }

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            height = min(height, heightWithStats)
        }

        val w = width.toFloat()
        val bh = barHeight.toFloat()

        paint.strokeWidth = bh
        leftArcRect.set(0F, 0F, bh, bh)
        rightArcRect.set(w - bh, 0F, w, bh)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (languages.isEmpty()) return

        drawLeftArcs(canvas)
        drawLanguageBars(canvas)
        drawRightArc(canvas)
        drawStats(canvas)
    }

    private fun drawLeftArcs(canvas: Canvas) {
        val lang = languages.firstKey()
        paint.color = languages[lang]?.first ?: error("Color not found")
        canvas.drawArc(leftArcRect, 90F, 180F, true, paint)
    }

    private fun drawRightArc(canvas: Canvas) {
        val lang = languages.lastKey()
        paint.color = languages[lang]?.first ?: error("Color not found")
        canvas.drawArc(rightArcRect, 270F, 180F, true, paint)
    }

    private fun drawLanguageBars(canvas: Canvas) {
        val oneByteLength = (width.toFloat() - barHeight) / totalBytes
        val languageBarY = barHeight / 2F
        var languageStart = barHeight / 2F

        for ((language, params) in languages) {
            val languageEnd = languageStart + language.byteCount * oneByteLength

            paint.color = params.first
            canvas.drawLine(languageStart, languageBarY, languageEnd, languageBarY, paint)

            languageStart = languageEnd
        }
    }

    private fun drawStats(canvas: Canvas) {
        val r = statsCircleRadius.toFloat()
        val entrySize = statEntrySize
        var statEntryStart = (barHeight + statsPadding).toFloat()

        for ((_, params) in languages) {
            val cy = statEntryStart + entrySize / 2

            paint.color = params.first
            canvas.drawCircle(r, cy, r, paint)

            val textPosition = statEntryStart + entrySize / 2 + statsTextSize / 4

            canvas.drawText(params.second, r * 3, textPosition, textPaint)

            statEntryStart += entrySize
        }
    }
}
