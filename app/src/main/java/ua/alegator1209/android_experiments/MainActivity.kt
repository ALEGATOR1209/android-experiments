package ua.alegator1209.android_experiments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.ui.activity.BaseActivity
import ua.alegator1209.core_ui.ui.fragments.FeatureFragment
import ua.alegator1209.feature_login.ui.LoginFragment
import ua.alegator1209.feature_profile.ui.ProfileFragment
import ua.alegator1209.feature_repositories.ui.RepositoryFragment

class MainActivity : BaseActivity() {
    override val Stage.fragment: FeatureFragment
        get() = when (this) {
            Stage.Login -> LoginFragment()
            Stage.Profile -> ProfileFragment()
            Stage.Repositories -> RepositoryFragment()
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
