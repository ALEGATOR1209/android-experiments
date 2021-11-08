package ua.alegator1209.core_ui.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.alegator1209.core.common.AppRouter
import ua.alegator1209.core.common.FeatureRouter
import ua.alegator1209.core.common.Phase

/** Part of the larger feature, usually one screen, that is managed by [FeatureRouterFragment].
 * Represents one [Phase] of the said feature. */
abstract class PhaseFragment<T : Phase> : BaseFragment() {
    val featureFragment: FeatureRouterFragment<T>
        get() {
            @Suppress("UNCHECKED_CAST")
            return parentFragment as FeatureRouterFragment<T>
        }

    protected val router: FeatureRouter<T> get() = featureFragment
    protected val appRouter: AppRouter get() = featureFragment.appRouter

    /** Lazily creates a [ViewModel] that can be shared across whole feature.
     * Owner of the [ViewModelProvider] store is parent [featureFragment]. */
    protected inline fun <reified K : ViewModel> featureViewModel() = lazy {
        ViewModelProvider(featureFragment)[K::class.java]
    }
}
