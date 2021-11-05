package ua.alegator1209.feature_repositories.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RepositoryDto(
    val id: Int,

    val name: String,

    @SerialName("full_name")
    val fullName: String,

    val private: Boolean,

    val url: String,

    val description: String? = null,

    @SerialName("fork")
    val isFork: Boolean,

    @SerialName("forks_count")
    val forksCount: Int = 0,

    @SerialName("stargazers_count")
    val stargazersCount: Int = 0,

    @SerialName("watchers_count")
    val watchersCount: Int = 0,

    val size: Int = 0,

    @SerialName("default_branch")
    val defaultBranch: String,

    @SerialName("open_issues_count")
    val openIssuesCount: Int = 0,

    @SerialName("is_template")
    val isTemplate: Boolean,

    val topics: List<String>,

    val archived: Boolean,

    val visibility: String,

    @SerialName("pushed_at")
    val pushedAt: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,
)
