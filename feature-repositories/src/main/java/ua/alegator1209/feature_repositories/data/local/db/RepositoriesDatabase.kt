package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RepositoryEntity::class,
        ContributorEntity::class,
        LanguageEntity::class,
    ],
    version = 2
)
internal abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
