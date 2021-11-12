package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "languages", primaryKeys = ["language", "repository_id"])
internal data class LanguageEntity(
    val language: String,

    @ColumnInfo(name = "byte_count")
    val byteCount: Int,

    @ColumnInfo(name = "repository_id")
    val repositoryId: Int,
)
