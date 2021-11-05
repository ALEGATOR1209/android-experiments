package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepositoryEntity::class], version = 1)
internal abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
