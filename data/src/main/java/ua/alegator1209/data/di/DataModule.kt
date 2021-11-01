package ua.alegator1209.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import ua.alegator1209.core.datasource.LoginDataSource
import ua.alegator1209.core.datasource.TokenDataSource
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.datasource.UserPersistentDataSource
import ua.alegator1209.core.di.PerApplication
import ua.alegator1209.core.domain.interactor.GetTokenUseCase
import ua.alegator1209.data.local.datasource.TokenLocalDataSource
import ua.alegator1209.data.local.datasource.UserLocalDataSource
import ua.alegator1209.data.remote.api.UserApi
import ua.alegator1209.data.remote.common.*
import ua.alegator1209.data.remote.common.client
import ua.alegator1209.data.remote.common.json
import ua.alegator1209.data.remote.common.retrofit
import ua.alegator1209.data.remote.datasource.LoginRemoteDataSource
import ua.alegator1209.data.remote.datasource.UserRemoteDataSource

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

    @Provides
    @PerApplication
    fun provideJson(): Json = json

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
    internal fun provideUserPersistentDataSource(
        context: Context,
        json: Json,
    ): UserPersistentDataSource = UserLocalDataSource(context, json)

    @Provides
    @PerApplication
    internal fun provideUserDataSource(
        api: UserApi
    ): UserDataSource = UserRemoteDataSource(api)

    @Provides
    @PerApplication
    internal fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.api()

    @Provides
    @PerApplication
    @NonAuthorized
    internal fun provideNonAuthorizedUserApi(
        @NonAuthorized retrofit: Retrofit
    ): UserApi = retrofit.api()

    @Provides
    @PerApplication
    internal fun provideLoginDataSource(
        @NonAuthorized api: UserApi
    ): LoginDataSource = LoginRemoteDataSource(api)

    @Provides
    @PerApplication
    fun provideTokenDataSource(context: Context): TokenDataSource = TokenLocalDataSource(context)
}
