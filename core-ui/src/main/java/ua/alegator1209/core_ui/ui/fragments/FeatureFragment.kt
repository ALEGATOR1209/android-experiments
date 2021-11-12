package ua.alegator1209.core_ui.ui.fragments

import ua.alegator1209.core.common.AppRouter

/** Basic abstraction for all navigable fragments.
 * Provides access to [AppRouter] to its descendants.
 * All top-level fragments should be inherited from **FeatureFragment**. */
sealed class FeatureFragment : BaseFragment() {
    val appRouter: AppRouter get() = baseActivity
    protected inline fun <reified T> provider() = lazy { baseApplication as T }
}
