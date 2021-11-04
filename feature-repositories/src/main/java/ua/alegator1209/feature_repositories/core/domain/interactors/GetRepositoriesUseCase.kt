package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import kotlin.math.max

internal class GetRepositoriesUseCase(
    private val remote: RepositoriesDataSource,
    private val local: RepositoriesCachingDataSource,
) {
    val PAGE_SIZE = 10
    private var page: Int = 0
    private var lastIndex = -1

    operator fun invoke(): Single<List<Repository>> = remote.getRepositories(PAGE_SIZE, page)
        .doOnSuccess { repos ->
            local.save(repos)
                .doOnError { it.printStackTrace() }
                .subscribe()
        }.onErrorResumeWith(
            Single.defer {
                local.getRepositories(PAGE_SIZE, page)
            }
        ).map { repos ->
            repos.filterIndexed { i, _ ->
                val pageIndex = page * PAGE_SIZE + i
                pageIndex > lastIndex
            }
        }.doOnSuccess { repos ->
            lastIndex = max(lastIndex, page * PAGE_SIZE + repos.lastIndex)
            if (repos.size >= PAGE_SIZE) page++
        }
}
