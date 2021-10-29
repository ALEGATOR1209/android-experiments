package ua.alegator1209.core_ui

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.datasource.TokenDataSource
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.di.PerApplication
import ua.alegator1209.core.domain.interactor.GetTokenUseCase
import ua.alegator1209.core.domain.interactor.GetUserUseCase
import ua.alegator1209.core.domain.interactor.LogInUseCase
import ua.alegator1209.core.domain.interactor.LogOutUseCase
import ua.alegator1209.core.domain.repository.TokenRepository
import ua.alegator1209.data.di.DataModule
import ua.alegator1209.data.di.NonAuthorized

fun Application.baseComponent(baseUrl: String) = DaggerBaseComponent
    .factory()
    .create(
        baseModule = BaseModule(this),
        dataModule = DataModule(baseUrl, this)
    )

@Component(modules = [BaseModule::class, DataModule::class])
@PerApplication
interface BaseComponent {
    fun appContext(): Context
    fun retrofit(): Retrofit
    @NonAuthorized fun nonAuthorizedRetrofit(): Retrofit
    fun getUserUseCase(): GetUserUseCase
    fun getTokenUseCase(): GetTokenUseCase
    fun logInUseCase(): LogInUseCase
    fun logOutUseCase(): LogOutUseCase

    @Component.Factory
    interface Factory {
        fun create(
            baseModule: BaseModule,
            dataModule: DataModule,
        ): BaseComponent
    }
}

@Module
class BaseModule(private val application: Application) {
    @Provides
    @PerApplication
    fun provideContext(): Context = application

    @Provides
    @PerApplication
    fun provideTokenRepository(dataSource: TokenDataSource) = TokenRepository(dataSource)

    @Provides
    @PerApplication
    fun provideGetTokenUseCase(repository: TokenRepository) = GetTokenUseCase(repository)

    @Provides
    @PerApplication
    fun provideGetUserUseCase(dataSource: UserDataSource) = GetUserUseCase(dataSource)

    @Provides
    @PerApplication
    fun provideLogInUseCase(
        userDataSource: UserDataSource,
        tokenRepository: TokenRepository
    ) = LogInUseCase(userDataSource, tokenRepository)

    @Provides
    @PerApplication
    fun provideLogOutUseCase(
        userDataSource: UserDataSource,
        tokenRepository: TokenRepository
    ) = LogOutUseCase(userDataSource, tokenRepository)
}
