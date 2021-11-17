package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.BranchesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.BranchesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class GetBranchesUseCase(
    private val local: BranchesCachingDataSource,
    private val remote: BranchesDataSource,
) {
    operator fun invoke(repository: Repository): Single<List<Branch>> {
        return remote[repository]
            .doOnSuccess {
                local.save(it, repository)
                    .subscribe({}, Throwable::printStackTrace)
            }.onErrorResumeWith(
                Single.defer {
                    local[repository]
                }
            )
    }
}
