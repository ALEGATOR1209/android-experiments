package ua.alegator1209.feature_profile.di

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core.domain.interactor.GetUserUseCase
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.data.remote.api
import ua.alegator1209.feature_profile.data.remote.api.GitHubUserApi
import ua.alegator1209.feature_profile.data.remote.datasource.UserRemoteDataSource
import ua.alegator1209.feature_profile.ui.ProfileViewModel
import javax.inject.Qualifier

@PerFeature
@Component(dependencies = [BaseComponent::class], modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(vm: ProfileViewModel)
}

fun interface ProfileComponentProvider {
    fun provideProfileComponent(): ProfileComponent
}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Profile

@Module
internal class ProfileModule {
    @Provides
    @PerFeature
    fun provideGitHubUserApi(retrofit: Retrofit): GitHubUserApi = retrofit.api()

    @Provides
    @PerFeature
    @Profile
    fun provideUserDataSource(api: GitHubUserApi): UserDataSource = UserRemoteDataSource(api)

    @Profile
    @Provides
    @PerFeature
    fun provideUseCase(
        @Profile dataSource: UserDataSource
    ): GetUserUseCase = GetUserUseCase(dataSource)
}
