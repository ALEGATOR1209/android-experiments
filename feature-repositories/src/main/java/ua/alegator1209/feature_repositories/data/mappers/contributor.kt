package ua.alegator1209.feature_repositories.data.mappers

import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.data.remote.model.ContributorDto

internal fun ContributorDto.toContributor() = Contributor(login, id, avatarUrl, contributions)
