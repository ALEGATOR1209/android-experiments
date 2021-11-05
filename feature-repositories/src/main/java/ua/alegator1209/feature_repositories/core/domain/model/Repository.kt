package ua.alegator1209.feature_repositories.core.domain.model

import java.util.*

internal data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val url: String,
    val description: String,
    val isFork: Boolean,
    val forksCount: Int = 0,
    val stargazersCount: Int = 0,
    val watchersCount: Int = 0,
    val size: Int = 0,
    val defaultBranch: String,
    val openIssuesCount: Int = 0,
    val isTemplate: Boolean,
    val topics: List<String>,
    val archived: Boolean,
    val visibility: String,
    val pushedAt: Date,
    val createdAt: Date,
    val updatedAt: Date,
)
