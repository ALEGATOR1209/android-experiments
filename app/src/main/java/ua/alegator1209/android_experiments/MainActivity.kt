package ua.alegator1209.android_experiments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.BaseActivity
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_login.di.LoginComponentProvider
import ua.alegator1209.feature_login.ui.LoginFragment
import ua.alegator1209.feature_profile.di.ProfileComponentProvider
import ua.alegator1209.feature_profile.ui.ProfileFragment
import ua.alegator1209.feature_repositories.di.RepositoryComponentProvider
import ua.alegator1209.feature_repositories.ui.RepositoriesFragment

class MainActivity : BaseActivity() {
    override val Stage.fragment: BaseFragment get() = when (this) {
        Stage.Login -> LoginFragment.newInstance(application as LoginComponentProvider)
        Stage.Profile -> ProfileFragment.newInstance(application as ProfileComponentProvider)
        Stage.Repositories -> RepositoriesFragment.newInstance(
            application as RepositoryComponentProvider
        )
    }
    override val backStack: MutableList<Stage> get() = viewModel.backStack

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTokenUseCase = baseApp.baseComponent.getTokenUseCase()
        viewModel.checkToken()
            .doOnSuccess { hasToken ->
                goTo(if (hasToken) Stage.Repositories else Stage.Login)
            }.doOnError {
                it.printStackTrace()
                goTo(Stage.Login)
            }.subscribe()
    }
}
