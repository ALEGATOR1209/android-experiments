package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface BranchesCachingDataSource : BranchesDataSource {
    fun save(branches: List<Branch>, repository: Repository): Completable
}
