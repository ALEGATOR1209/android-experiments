package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.BranchesCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.BranchEntity
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.mappers.toBranch
import ua.alegator1209.feature_repositories.data.mappers.toEntity

internal class BranchesLocalCachingDataSource(
    private val dao: RepositoryDao,
) : BranchesCachingDataSource {
    override fun save(branches: List<Branch>, repository: Repository): Completable {
        return dao.clearBranches(repository.id).andThen(
            dao.saveBranches(
                branches.map { it.toEntity(repository) }
            )
        )
    }

    override fun get(repository: Repository): Single<List<Branch>> {
        return dao.getBranches(repository.id)
            .map { it.map(BranchEntity::toBranch) }
    }
}
