package ua.alegator1209.feature_repositories.di

import dagger.Module
import dagger.Provides
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.feature_repositories.core.datasource.LanguagesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.LanguagesDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetLanguagesUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.LanguagesLocalCachingDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.LanguagesRemoteDataSource

@Module
internal class LanguagesModule {
    @Provides
    @PerFeature
    fun provideGetLanguagesUseCase(
        local: LanguagesCachingDataSource,
        remote: LanguagesDataSource
    ) = GetLanguagesUseCase(local, remote)

    @Provides
    @PerFeature
    fun provideLocalDataSource(dao: RepositoryDao): LanguagesCachingDataSource {
        return LanguagesLocalCachingDataSource(dao)
    }

    @Provides
    @PerFeature
    fun provideRemoteDataSource(api: RepositoriesApi): LanguagesDataSource {
        return LanguagesRemoteDataSource(api)
    }
}
