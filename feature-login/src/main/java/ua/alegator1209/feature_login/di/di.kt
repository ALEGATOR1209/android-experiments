package ua.alegator1209.feature_login.di

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.data.api
import ua.alegator1209.feature_login.core.datasource.LoginDataSource
import ua.alegator1209.feature_login.core.domain.interactor.LoginUseCase
import ua.alegator1209.feature_login.core.domain.interactor.LoginUseCaseImpl
import ua.alegator1209.feature_login.data.remote.api.LoginApi
import ua.alegator1209.feature_login.data.remote.datasource.LoginRemoteDataSource
import ua.alegator1209.feature_login.ui.LoginViewModel

@Component(
    modules = [LoginModule::class],
    dependencies = [BaseComponent::class],
)
@PerFeature
interface LoginComponent {
    fun inject(vm: LoginViewModel)
}

fun interface LoginComponentProvider {
    fun provideLoginComponent(): LoginComponent
}


@Module
class LoginModule {
    @Provides
    @PerFeature
    internal fun provideApi(retrofit: Retrofit): LoginApi = retrofit.api()

    @Provides
    @PerFeature
    internal fun provideLoginDataSource(
        api: LoginApi
    ): LoginDataSource = LoginRemoteDataSource(api)

    @Provides
    @PerFeature
    internal fun provideUseCase(
        loginDataSource: LoginDataSource
    ): LoginUseCase = LoginUseCaseImpl(loginDataSource)
}
