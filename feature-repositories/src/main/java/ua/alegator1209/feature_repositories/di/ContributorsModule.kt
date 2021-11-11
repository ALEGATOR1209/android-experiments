package ua.alegator1209.feature_repositories.di

import dagger.Module
import dagger.Provides
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetContributorsUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.ContributorsLocalCachingDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.ContributorsRemoteDataSource

@Module
internal class ContributorsModule {
    @Provides
    @PerFeature
    internal fun provideGetContributorsUseCase(
        remote: ContributorsDataSource,
        local: ContributorsCachingDataSource
    ) = GetContributorsUseCase(remote, local)

    @Provides
    @PerFeature
    internal fun provideContributorsRemoteDataSource(
        api: RepositoriesApi
    ): ContributorsDataSource = ContributorsRemoteDataSource(api)

    @Provides
    @PerFeature
    internal fun provideContributorsLocalDataSource(
        dao: RepositoryDao
    ): ContributorsCachingDataSource {
        return ContributorsLocalCachingDataSource(dao)
    }
}
