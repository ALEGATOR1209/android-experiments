package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.mappers.toUser
import ua.alegator1209.data.model.UserDto
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi

internal class ContributorsRemoteDataSource(
    private val api: RepositoriesApi
) : ContributorsDataSource {
    override fun getContributors(owner: String, repository: String): Single<List<User>> {
        return api.getContributors(owner, repository).map {
            it.map(UserDto::toUser)
        }
    }
}
