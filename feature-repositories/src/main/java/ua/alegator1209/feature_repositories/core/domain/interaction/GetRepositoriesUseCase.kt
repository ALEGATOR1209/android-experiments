package ua.alegator1209.feature_repositories.core.domain.interaction

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository

private const val PAGE_SIZE = 10

internal class GetRepositoriesUseCase(
    private val dataSource: RepositoriesDataSource
) {
    private var page: Int = 0

    operator fun invoke(): Single<List<Repository>> = dataSource.getRepositories(PAGE_SIZE, page++)
}
