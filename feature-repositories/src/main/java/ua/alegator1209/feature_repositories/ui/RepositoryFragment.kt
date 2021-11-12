package ua.alegator1209.feature_repositories.ui

import android.os.Bundle
import android.view.View
import ua.alegator1209.core_ui.ui.fragments.FeatureRouterFragment
import ua.alegator1209.core_ui.ui.fragments.PhaseFragment
import ua.alegator1209.feature_repositories.di.RepositoryComponentProvider
import ua.alegator1209.feature_repositories.routing.RepositoryPhase

class RepositoryFragment : FeatureRouterFragment<RepositoryPhase>() {
    override val RepositoryPhase.fragment: PhaseFragment<RepositoryPhase>
        get() = when (this) {
            RepositoryPhase.Detailed -> RepositoryDetailsFragment()
            RepositoryPhase.List -> RepositoryListFragment()
        }

    private val provider: RepositoryComponentProvider by provider()
    private val viewModel: RepositoryViewModel by viewModel()

    override val featureBackStack: MutableList<RepositoryPhase>
        get() = viewModel.backStack

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.provideRepositoryComponent().inject(viewModel)
        goTo(featureBackStack.lastOrNull() ?: RepositoryPhase.List)
    }
}
