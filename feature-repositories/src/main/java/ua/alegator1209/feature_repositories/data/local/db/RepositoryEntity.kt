package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
internal data class RepositoryEntity(
    @PrimaryKey
    val id: Int,

    val name: String,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "is_private")
    val isPrivate: Boolean,

    val url: String,

    val description: String,

    @ColumnInfo(name = "is_fork")
    val isFork: Boolean,

    @ColumnInfo(name = "forks_count")
    val forksCount: Int,

    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Int,

    @ColumnInfo(name = "watchers_count")
    val watchersCount: Int,

    val size: Int,

    @ColumnInfo(name = "default_branch")
    val defaultBranch: String,

    @ColumnInfo(name = "open_issues_count")
    val openIssuesCount: Int,

    @ColumnInfo(name = "is_template")
    val isTemplate: Boolean,

    val topics: String,

    val archived: Boolean,

    val visibility: String,

    @ColumnInfo(name = "pushed_at")
    val pushedAt: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
)
