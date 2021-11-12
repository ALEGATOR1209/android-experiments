package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.data.mappers.toContributor
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.model.ContributorDto

internal class ContributorsRemoteDataSource(
    private val api: RepositoriesApi
) : ContributorsDataSource {
    override fun getContributors(owner: String, repository: String): Single<List<Contributor>> {
        return api.getContributors(owner, repository).map {
            it.map(ContributorDto::toContributor)
        }
    }
}
