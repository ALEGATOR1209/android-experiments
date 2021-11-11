package ua.alegator1209.feature_repositories.data.mappers

import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.ContributorEntity
import ua.alegator1209.feature_repositories.data.remote.model.ContributorDto

internal fun ContributorDto.toContributor() = Contributor(login, id, avatarUrl, contributions)
internal fun ContributorEntity.toContributor() = Contributor(login, id, avatarUrl, contributions)
internal fun Contributor.toEntity(repository: Repository) = ContributorEntity(
    login, id, avatarUrl, contributions, repository.id
)
