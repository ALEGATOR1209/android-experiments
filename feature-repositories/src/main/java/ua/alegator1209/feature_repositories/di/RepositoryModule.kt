package ua.alegator1209.feature_repositories.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.data.local.common.db
import ua.alegator1209.data.remote.common.api
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.SelectRepositoryUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.RepositoriesCachingLocalDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoriesDatabase
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.RepositoriesRemoteDataSource

@Module(
    includes = [
        ContributorsModule::class,
        LanguagesModule::class,
        TopicsModule::class,
        BranchesModule::class,
    ]
)
class RepositoryModule {
    @Provides
    @PerFeature
    internal fun provideGetRepositoriesUseCase(
        remote: RepositoriesDataSource,
        local: RepositoriesCachingDataSource
    ) = GetRepositoriesUseCase(remote, local)

    @Provides
    @PerFeature
    internal fun provideSelectRepositoryUseCase() = SelectRepositoryUseCase()

    @Provides
    @PerFeature
    internal fun provideRepositoriesRemoteDataSource(
        api: RepositoriesApi
    ): RepositoriesDataSource = RepositoriesRemoteDataSource(api)

    @Provides
    @PerFeature
    internal fun provideRepositoriesLocalDataSource(
        dao: RepositoryDao
    ): RepositoriesCachingDataSource = RepositoriesCachingLocalDataSource(dao)

    @Provides
    @PerFeature
    internal fun provideDao(
        db: RepositoriesDatabase
    ): RepositoryDao = db.repositoryDao()

    @Provides
    @PerFeature
    internal fun provideDb(context: Context): RepositoriesDatabase = db(
        context,
        RepositoriesDatabase.Migrations.FROM_2_TO_3,
    )

    @Provides
    @PerFeature
    internal fun provideApi(retrofit: Retrofit): RepositoriesApi = retrofit.api()
}
