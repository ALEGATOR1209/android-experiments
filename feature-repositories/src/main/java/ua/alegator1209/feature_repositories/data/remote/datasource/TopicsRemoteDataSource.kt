package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.TopicsDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.core.domain.model.owner
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi

internal class TopicsRemoteDataSource(
    private val api: RepositoriesApi
) : TopicsDataSource {
    override fun getTopics(repository: Repository): Single<List<String>> {
        return api.getTopics(repository.owner, repository.name)
            .map { it.names }
    }
}
