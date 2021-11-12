package ua.alegator1209.android_experiments

import ua.alegator1209.core_ui.di.baseComponent
import ua.alegator1209.core_ui.ui.application.BaseApplication
import ua.alegator1209.feature_login.di.DaggerLoginComponent
import ua.alegator1209.feature_login.di.LoginComponent
import ua.alegator1209.feature_login.di.LoginComponentProvider
import ua.alegator1209.feature_profile.di.DaggerProfileComponent
import ua.alegator1209.feature_profile.di.ProfileComponent
import ua.alegator1209.feature_profile.di.ProfileComponentProvider
import ua.alegator1209.feature_repositories.di.DaggerRepositoryComponent
import ua.alegator1209.feature_repositories.di.RepositoryComponent
import ua.alegator1209.feature_repositories.di.RepositoryComponentProvider

class MainApplication :
    BaseApplication(),
    LoginComponentProvider,
    ProfileComponentProvider,
    RepositoryComponentProvider {
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

    override fun provideRepositoryComponent(): RepositoryComponent {
        return DaggerRepositoryComponent.builder()
            .baseComponent(baseComponent)
            .build()
    }
}
