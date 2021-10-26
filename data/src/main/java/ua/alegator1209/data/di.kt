package ua.alegator1209.data

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerApplication

@Module
class DataModule(
    private val baseUrl: String,
    private val context: Context,
) {
    @Provides
    @PerApplication
    fun provideRetrofit(): Retrofit = retrofit(baseUrl, client(context))
}
