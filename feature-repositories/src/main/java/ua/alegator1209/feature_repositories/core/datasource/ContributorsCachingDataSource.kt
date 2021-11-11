package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface ContributorsCachingDataSource {
    fun save(contributors: List<Contributor>, repository: Repository): Completable
    operator fun get(repository: Repository): Single<List<Contributor>>
}
