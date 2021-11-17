package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
internal data class TopicsRecordEntity(
    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    val repositoryId: Int,
    val topics: String = "",
)
