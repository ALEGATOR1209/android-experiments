package ua.alegator1209.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.di.PerApplication
import ua.alegator1209.data.local.datasource.UserLocalDataSource
import ua.alegator1209.data.remote.client
import ua.alegator1209.data.remote.retrofit

@Module
class DataModule(
    private val baseUrl: String,
    private val context: Context,
) {
    @Provides
    @PerApplication
    fun provideRetrofit(): Retrofit = retrofit(baseUrl, client(context))

    @Provides
    @PerApplication
    fun provideUserDataSource(): UserDataSource = UserLocalDataSource()
}
