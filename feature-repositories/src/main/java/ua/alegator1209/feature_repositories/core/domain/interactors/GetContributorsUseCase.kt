package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.ContributorsDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class GetContributorsUseCase(
    private val remote: ContributorsDataSource,
    private val local: ContributorsCachingDataSource,
) {
    operator fun invoke(repository: Repository): Flowable<List<Contributor>> =
        local[repository]
            .concatWith(
                Single.defer {
                    remote.getContributors(
                        repository.fullName.substringBefore('/'),
                        repository.name
                    ).doOnSuccess { contributors -> cache(contributors, repository) }
                }
            )

    private fun cache(contributors: List<Contributor>, repository: Repository) {
        local.save(contributors, repository)
            .doOnError { it.printStackTrace() }
            .subscribe()
    }
}
