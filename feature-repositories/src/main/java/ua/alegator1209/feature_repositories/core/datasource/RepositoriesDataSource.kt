package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface RepositoriesDataSource {
    fun getRepositories(
        perPage: Int,
        page: Int,
    ): Single<List<Repository>>
}
