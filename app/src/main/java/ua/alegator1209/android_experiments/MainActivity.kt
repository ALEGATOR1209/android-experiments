package ua.alegator1209.android_experiments

import android.os.Bundle
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.BaseActivity
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_login.di.LoginComponentProvider
import ua.alegator1209.feature_login.ui.LoginFragment
import ua.alegator1209.feature_profile.di.ProfileComponentProvider
import ua.alegator1209.feature_profile.ui.ProfileFragment

class MainActivity : BaseActivity() {
    override val Stage.fragment: BaseFragment get() = when (this) {
        Stage.Login -> LoginFragment.newInstance(application as LoginComponentProvider)
        Stage.Profile -> ProfileFragment.newInstance(application as ProfileComponentProvider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goTo(Stage.Login)
    }
}
