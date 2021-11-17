package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class GetRepositoriesUseCase(
    private val remote: RepositoriesDataSource,
    private val local: RepositoriesCachingDataSource,
) {
    val PAGE_SIZE = 10
    private var cache = linkedSetOf<Repository>()

    operator fun invoke(page: Int, fromIndex: Int): Single<List<Repository>> {
        val pageStartIndex = page * PAGE_SIZE
        val pageEndIndex = pageStartIndex + PAGE_SIZE - 1

        return if (cache.size >= pageEndIndex) {
            Single.just(cache.toList().subList(pageStartIndex + fromIndex, pageEndIndex + 1))
        } else {
            getPage(page, fromIndex)
        }
    }

    private fun getPage(pageNum: Int, fromIndex: Int) = remote.getRepositories(PAGE_SIZE, pageNum)
        .retry(2)
        .map { it.drop(fromIndex) }
        .onErrorResumeWith(
            Single.defer {
                local.getRepositories(PAGE_SIZE, pageNum)
            }
        ).doOnSuccess(this::save)

    private fun save(repositories: List<Repository>) {
        cache.addAll(repositories)
        local.save(repositories)
            .subscribe({}, Throwable::printStackTrace)
    }
}
