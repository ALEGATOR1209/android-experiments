package ua.alegator1209.core_ui

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerApplication
import ua.alegator1209.data.DataModule

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
}
