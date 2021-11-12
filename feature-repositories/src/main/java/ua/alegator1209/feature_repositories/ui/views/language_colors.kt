package ua.alegator1209.feature_repositories.ui.views

import android.content.Context
import androidx.core.content.ContextCompat
import ua.alegator1209.feature_repositories.R
import ua.alegator1209.feature_repositories.core.domain.model.Language

private val LANGUAGE_COLORS = mapOf(
    "kotlin" to R.color.kotlin,
    "java" to R.color.java,
    "c#" to R.color.c_sharp,
    "assembly" to R.color.assembly,
    "javascript" to R.color.javascript,
    "go" to R.color.go,
    "python" to R.color.python,
    "ruby" to R.color.ruby,
)

internal fun Context.colorFor(language: Language): Int {
    val colorRes = LANGUAGE_COLORS[language.name.lowercase()] ?: R.color.language_default
    return ContextCompat.getColor(this, colorRes)
}
