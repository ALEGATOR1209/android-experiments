package ua.alegator1209.feature_repositories.data.remote.mappers

import ua.alegator1209.data.mappers.toDate
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.remote.model.RepositoryDto

internal fun RepositoryDto.toRepository() = Repository(
    id = id,
    name = name,
    fullName = fullName,
    private = private,
    url = url,
    description = description ?: "",
    isFork = isFork,
    forksCount = forksCount,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    size = size,
    defaultBranch = defaultBranch,
    openIssuesCount = openIssuesCount,
    isTemplate = isTemplate,
    topics = topics,
    archived = archived,
    visibility = visibility,
    pushedAt = pushedAt.toDate(),
    createdAt = createdAt.toDate(),
    updatedAt = updatedAt.toDate(),
)
