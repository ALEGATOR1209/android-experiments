package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.BranchesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.core.domain.model.owner
import ua.alegator1209.feature_repositories.data.mappers.toBranch
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.model.BranchDto

internal class BranchesRemoteDataSource(
    private val api: RepositoriesApi,
) : BranchesDataSource {
    override fun get(repository: Repository): Single<List<Branch>> {
        return api.getBranches(repository.owner, repository.name)
            .map { branches -> branches.map(BranchDto::toBranch) }
    }
}
