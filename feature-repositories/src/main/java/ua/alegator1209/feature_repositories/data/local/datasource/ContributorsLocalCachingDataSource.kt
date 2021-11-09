package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource

internal class ContributorsLocalCachingDataSource : ContributorsCachingDataSource {
    override fun saveContributors(contributors: List<User>): Completable {
        return Completable.complete()
    }

    override fun getContributors(owner: String, repository: String): Single<List<User>> {
        return Single.just(listOf())
    }
}
