package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.domain.model.Contributor

internal interface ContributorsDataSource {
    fun getContributors(owner: String, repository: String): Single<List<Contributor>>
}
