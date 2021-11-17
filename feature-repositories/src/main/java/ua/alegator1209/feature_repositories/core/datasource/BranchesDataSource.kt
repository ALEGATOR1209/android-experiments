package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface BranchesDataSource {
    operator fun get(repository: Repository): Single<List<Branch>>
}
