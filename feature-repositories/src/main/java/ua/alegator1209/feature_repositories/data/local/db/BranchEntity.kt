package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branches")
data class BranchEntity(
    @ColumnInfo(name = "repository_id")
    val repositoryId: Int,
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "is_protected")
    val isProtected: Boolean,
)
