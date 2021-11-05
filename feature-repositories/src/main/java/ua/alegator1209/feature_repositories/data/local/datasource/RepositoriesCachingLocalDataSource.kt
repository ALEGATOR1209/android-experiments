package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.local.db.RepositoryEntity
import ua.alegator1209.feature_repositories.data.mappers.toEntity
import ua.alegator1209.feature_repositories.data.mappers.toRepository

internal class RepositoriesCachingLocalDataSource(
    private val dao: RepositoryDao,
) : RepositoriesCachingDataSource {
    override fun save(repositories: List<Repository>): Completable = repositories
        .map(Repository::toEntity)
        .let(dao::saveRepositories)

    override fun getRepositories(
        perPage: Int,
        page: Int
    ): Single<List<Repository>> = dao.getRepositories(perPage, page)
        .map {
            it.map(RepositoryEntity::toRepository)
        }
}
