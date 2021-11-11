package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.ContributorsCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.ContributorEntity
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.mappers.toContributor
import ua.alegator1209.feature_repositories.data.mappers.toEntity

internal class ContributorsLocalCachingDataSource(
    private val dao: RepositoryDao
) : ContributorsCachingDataSource {
    override fun save(contributors: List<Contributor>, repository: Repository): Completable {
        return dao.saveContributors(contributors.map { it.toEntity(repository) })
    }

    override fun get(repository: Repository): Single<List<Contributor>> {
        return dao.getContributors(repository.id).map { contributors ->
            contributors.map(ContributorEntity::toContributor)
        }
    }
}
