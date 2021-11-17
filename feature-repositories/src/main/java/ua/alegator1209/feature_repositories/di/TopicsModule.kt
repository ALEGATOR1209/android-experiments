package ua.alegator1209.feature_repositories.di

import dagger.Module
import dagger.Provides
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.feature_repositories.core.datasource.TopicsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.TopicsDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetTopicsUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.TopicsLocalCachingDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.TopicsRemoteDataSource

@Module
internal class TopicsModule {
    @Provides
    @PerFeature
    internal fun provideGetTopicsUseCase(
        local: TopicsCachingDataSource,
        remote: TopicsDataSource,
    ) = GetTopicsUseCase(local, remote)

    @Provides
    @PerFeature
    internal fun provideTopicsRemoteDataSource(
        api: RepositoriesApi
    ): TopicsDataSource = TopicsRemoteDataSource(api)

    @Provides
    @PerFeature
    internal fun provideTopicsLocalDataSource(
        dao: RepositoryDao
    ): TopicsCachingDataSource {
        return TopicsLocalCachingDataSource(dao)
    }
}
