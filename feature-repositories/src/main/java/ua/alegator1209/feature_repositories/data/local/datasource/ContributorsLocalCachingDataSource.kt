package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Contributor

internal class ContributorsLocalCachingDataSource : ContributorsCachingDataSource {
    override fun saveContributors(contributors: List<Contributor>): Completable {
        return Completable.complete()
    }

    override fun getContributors(owner: String, repository: String): Single<List<Contributor>> {
        return Single.just(listOf())
    }
}
