package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.mappers.toRepository
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.model.RepositoryDto

internal class RepositoriesRemoteDataSource(
    private val api: RepositoriesApi
) : RepositoriesDataSource {
    override fun getRepositories(perPage: Int, page: Int): Single<List<Repository>> {
        return api.getRepos(perPage, page + 1).map { repos ->
            repos.map(RepositoryDto::toRepository)
        }
    }
}
