package ua.alegator1209.feature_repositories.data.mappers

import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.BranchEntity
import ua.alegator1209.feature_repositories.data.remote.model.BranchDto

internal fun BranchDto.toBranch() = Branch(name, protected)
internal fun BranchEntity.toBranch() = Branch(name, isProtected)
internal fun Branch.toEntity(repository: Repository) = BranchEntity(
    repositoryId = repository.id,
    name = name,
    isProtected = protected,
)
