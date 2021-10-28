package ua.alegator1209.android_experiments

import ua.alegator1209.core_ui.BaseApplication
import ua.alegator1209.core_ui.baseComponent
import ua.alegator1209.feature_login.di.DaggerLoginComponent
import ua.alegator1209.feature_login.di.LoginComponent
import ua.alegator1209.feature_login.di.LoginComponentProvider
import ua.alegator1209.feature_profile.di.DaggerProfileComponent
import ua.alegator1209.feature_profile.di.ProfileComponent
import ua.alegator1209.feature_profile.di.ProfileComponentProvider

class MainApplication : BaseApplication(),
    LoginComponentProvider,
    ProfileComponentProvider
{
    override val baseComponent by lazy { baseComponent("https://api.github.com") }

    override fun provideLoginComponent(): LoginComponent {
        return DaggerLoginComponent.builder()
            .baseComponent(baseComponent)
            .build()
    }

    override fun provideProfileComponent(): ProfileComponent {
        return DaggerProfileComponent.builder()
            .baseComponent(baseComponent)
            .build()
    }
}
