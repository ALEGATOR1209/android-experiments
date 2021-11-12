package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contributors")
data class ContributorEntity(
    val login: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val contributions: Int,
    @ColumnInfo(name = "repository_id")
    val repositoryId: Int,
)
