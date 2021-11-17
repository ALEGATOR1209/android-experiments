package ua.alegator1209.feature_repositories.di

import dagger.Module
import dagger.Provides
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.feature_repositories.core.datasource.BranchesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.BranchesDataSource
import ua.alegator1209.feature_repositories.core.domain.interactors.GetBranchesUseCase
import ua.alegator1209.feature_repositories.data.local.datasource.BranchesLocalCachingDataSource
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.BranchesRemoteDataSource

@Module
internal class BranchesModule {
    @Provides
    @PerFeature
    internal fun provideGetBranchesUseCase(
        local: BranchesCachingDataSource,
        remote: BranchesDataSource,
    ) = GetBranchesUseCase(local, remote)

    @Provides
    @PerFeature
    internal fun provideBranchesRemoteDataSource(
        api: RepositoriesApi
    ): BranchesDataSource = BranchesRemoteDataSource(api)

    @Provides
    @PerFeature
    internal fun provideBranchesLocalDataSource(
        dao: RepositoryDao
    ): BranchesCachingDataSource {
        return BranchesLocalCachingDataSource(dao)
    }
}
