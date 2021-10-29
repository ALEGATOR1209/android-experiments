package ua.alegator1209.feature_login.di

import dagger.Component
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.feature_login.ui.LoginViewModel

@Component(dependencies = [BaseComponent::class])
@PerFeature
interface LoginComponent {
    fun inject(vm: LoginViewModel)
}

fun interface LoginComponentProvider {
    fun provideLoginComponent(): LoginComponent
}
