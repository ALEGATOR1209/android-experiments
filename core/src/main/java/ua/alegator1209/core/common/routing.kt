package ua.alegator1209.core.common

interface Navigable

/** **Stage** is a application-level navigation unit.
 * One stage can consist of one or more screens united by single logic
 * or action flow. Each stage is a black box that is viewed by the rest
 * of app as independent feature. */
sealed class Stage : Navigable {
    object Login : Stage()
    object Profile : Stage()
    object Repositories : Stage()
}

/** **Phase** is a feature-level navigation unit.
 * In most cases it's the single screen or state of the broader flow or feature.
 * While [Stage]s are managed on the app level, **Phases** are managed independently
 * by corresponding features.
 *
 * This interface is made with intent to be expanded by different flows, e.g. Repositories, that
 * would define their concrete Phases of transition. */
interface Phase : Navigable

interface Router<in T : Navigable> {
    fun goTo(navigable: T, saveToBackStack: Boolean = true)
    fun returnTo(navigable: T)
    fun replaceTo(navigable: T)
    fun back()
}

interface AppRouter : Router<Stage>
interface FeatureRouter<in T : Phase> : Router<T>
