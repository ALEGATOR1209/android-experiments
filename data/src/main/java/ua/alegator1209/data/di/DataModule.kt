package ua.alegator1209.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit
import ua.alegator1209.core.datasource.TokenDataSource
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.di.PerApplication
import ua.alegator1209.core.domain.interactor.GetTokenUseCase
import ua.alegator1209.data.local.datasource.TokenLocalDataSource
import ua.alegator1209.data.local.datasource.UserLocalDataSource
import ua.alegator1209.data.remote.AuthInterceptor
import ua.alegator1209.data.remote.client
import ua.alegator1209.data.remote.retrofit

@Module
class DataModule(
    private val baseUrl: String,
    private val context: Context,
) {
    @ExperimentalSerializationApi
    @Provides
    @PerApplication
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit = retrofit(
        baseUrl,
        client(context, authInterceptor)
    )

    @ExperimentalSerializationApi
    @NonAuthorized
    @Provides
    @PerApplication
    fun provideNonAuthorizedRetrofit(): Retrofit = retrofit(baseUrl, client(context))

    @Provides
    @PerApplication
    fun provideAuthInterceptor(useCase: GetTokenUseCase) = AuthInterceptor(useCase)

    @Provides
    @PerApplication
    fun provideUserDataSource(): UserDataSource = UserLocalDataSource()

    @Provides
    @PerApplication
    fun provideTokenDataSource(context: Context): TokenDataSource = TokenLocalDataSource(context)
}
