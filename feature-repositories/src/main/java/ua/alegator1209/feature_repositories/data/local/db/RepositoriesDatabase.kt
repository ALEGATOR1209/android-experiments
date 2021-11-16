package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        RepositoryEntity::class,
        ContributorEntity::class,
        LanguageEntity::class,
        TopicsRecordEntity::class,
    ],
    version = 3
)
internal abstract class RepositoriesDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    object Migrations {
        val FROM_2_TO_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                        CREATE TABLE topics (
                            repository_id INTEGER NOT NULL,
                            topics TEXT NOT NULL,
                            PRIMARY KEY (repository_id)
                        )
                    """.trimMargin()
                )
            }
        }
    }
}
