package ua.alegator1209.core_ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.alegator1209.core.common.FeatureRouter
import ua.alegator1209.core.common.Phase
import ua.alegator1209.core_ui.R
import ua.alegator1209.core_ui.animation.AnimationSet
import ua.alegator1209.core_ui.databinding.FragmentFeatureBinding

/** [FeatureFragment] with ability to have nested fragments and control whole feature consisting of
 * multiple [PhaseFragment]s. Implements routing features and allows data transfer between
 * feature [Phase]s. */
abstract class FeatureRouterFragment<in T : Phase> : FeatureFragment(), FeatureRouter<T> {
    protected abstract val T.fragment: PhaseFragment<T>

    private var _binding: FragmentFeatureBinding? = null
    private val binding: FragmentFeatureBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeFragment(
        fragment: BaseFragment,
        animation: AnimationSet? = null,
    ) {
        childFragmentManager.beginTransaction().apply {
            if (animation != null) setCustomAnimations(animation.enter, animation.exit)
            replace(R.id.container, fragment)
        }.commit()
    }

    override fun goTo(navigable: T, saveToBackStack: Boolean) {
        changeFragment(navigable.fragment, AnimationSet.Forward)
    }

    override fun returnTo(navigable: T) {
        changeFragment(navigable.fragment, AnimationSet.Backward)
    }

    override fun replaceTo(navigable: T) {
        changeFragment(navigable.fragment)
    }

    override fun back() {
        appRouter.back()
    }
}
