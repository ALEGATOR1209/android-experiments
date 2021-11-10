package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Contributor

internal class GetContributorsUseCase(
    private val remote: ContributorsDataSource,
    private val local: ContributorsCachingDataSource,
) {
    operator fun invoke(owner: String, repository: String): Flowable<List<Contributor>> =
        local.getContributors(owner, repository).concatWith(
            Single.defer {
                remote.getContributors(owner, repository).doOnSuccess(this::cache)
            }
        )

    private fun cache(contributors: List<Contributor>) {
        local.saveContributors(contributors)
            .doOnError { it.printStackTrace() }
            .subscribe()
    }
}
