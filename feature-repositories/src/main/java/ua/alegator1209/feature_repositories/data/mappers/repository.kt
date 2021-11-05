package ua.alegator1209.feature_repositories.data.mappers

import ua.alegator1209.data.mappers.toDate
import ua.alegator1209.data.mappers.toDateString
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.RepositoryEntity
import ua.alegator1209.feature_repositories.data.remote.model.RepositoryDto

internal fun RepositoryDto.toRepository() = Repository(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = private,
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

internal fun RepositoryEntity.toRepository() = Repository(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = isPrivate,
    url = url,
    description = description,
    isFork = isFork,
    forksCount = forksCount,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    size = size,
    defaultBranch = defaultBranch,
    openIssuesCount = openIssuesCount,
    isTemplate = isTemplate,
    topics = topics.split(";"),
    archived = archived,
    visibility = visibility,
    pushedAt = pushedAt.toDate(),
    createdAt = createdAt.toDate(),
    updatedAt = updatedAt.toDate(),
)

internal fun Repository.toEntity() = RepositoryEntity(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = isPrivate,
    url = url,
    description = description,
    isFork = isFork,
    forksCount = forksCount,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    size = size,
    defaultBranch = defaultBranch,
    openIssuesCount = openIssuesCount,
    isTemplate = isTemplate,
    topics = topics.joinToString(";"),
    archived = archived,
    visibility = visibility,
    pushedAt = pushedAt.toDateString(),
    createdAt = createdAt.toDateString(),
    updatedAt = updatedAt.toDateString(),
)
