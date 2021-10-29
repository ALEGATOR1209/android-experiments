package ua.alegator1209.feature_profile.di

import dagger.Component
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.feature_profile.ui.ProfileViewModel

@PerFeature
@Component(dependencies = [BaseComponent::class])
interface ProfileComponent {
    fun inject(vm: ProfileViewModel)
}

fun interface ProfileComponentProvider {
    fun provideProfileComponent(): ProfileComponent
}
