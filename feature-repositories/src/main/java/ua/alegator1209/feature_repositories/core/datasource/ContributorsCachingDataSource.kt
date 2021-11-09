package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.model.User

internal interface ContributorsCachingDataSource : ContributorsDataSource {
    fun saveContributors(contributors: List<User>): Completable
}
