package ua.alegator1209.android_experiments

import android.os.Bundle
import ua.alegator1209.core_ui.BaseActivity
import ua.alegator1209.feature_login.di.LoginComponentProvider
import ua.alegator1209.feature_login.ui.LoginFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(LoginFragment.newInstance(baseApp as LoginComponentProvider))
    }
}
