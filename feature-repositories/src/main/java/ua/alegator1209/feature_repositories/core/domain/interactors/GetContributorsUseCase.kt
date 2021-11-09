package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource

internal class GetContributorsUseCase(
    private val remote: ContributorsDataSource,
    private val local: ContributorsCachingDataSource,
) {
    operator fun invoke(owner: String, repository: String): Flowable<List<User>> =
        local.getContributors(owner, repository).concatWith(
            Single.defer {
                remote.getContributors(owner, repository).doOnSuccess(this::cache)
            }
        )

    private fun cache(contributors: List<User>) {
        local.saveContributors(contributors)
            .doOnError { it.printStackTrace() }
            .subscribe()
    }
}
