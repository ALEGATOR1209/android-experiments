package ua.alegator1209.feature_repositories.di

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.data.local.common.db
import ua.alegator1209.data.remote.common.api
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.RepositoriesCachingLocalDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoriesDatabase
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.RepositoriesRemoteDataSource
import ua.alegator1209.feature_repositories.ui.RepositoriesViewModel

@PerFeature
@Component(modules = [RepositoryModule::class], dependencies = [BaseComponent::class])
interface RepositoryComponent {
    fun inject(viewModel: RepositoriesViewModel)
}

interface RepositoryComponentProvider {
    fun provideRepositoryComponent(): RepositoryComponent
}

@Module
class RepositoryModule {
    @Provides
    @PerFeature
    internal fun provideUseCase(
        remote: RepositoriesDataSource,
        local: RepositoriesCachingDataSource
    ) = GetRepositoriesUseCase(remote, local)

    @Provides
    @PerFeature
    internal fun provideRemoteDataSource(
        user: User,
        api: RepositoriesApi
    ): RepositoriesDataSource = RepositoriesRemoteDataSource(api, user.name)

    @Provides
    @PerFeature
    internal fun provideLocalDataSource(
        dao: RepositoryDao
    ): RepositoriesCachingDataSource = RepositoriesCachingLocalDataSource(dao)

    @Provides
    @PerFeature
    internal fun provideDao(
        db: RepositoriesDatabase
    ): RepositoryDao = db.repositoryDao()

    @Provides
    @PerFeature
    internal fun provideDb(context: Context): RepositoriesDatabase = db(context)

    @Provides
    @PerFeature
    internal fun provideApi(retrofit: Retrofit): RepositoriesApi = retrofit.api()
}
