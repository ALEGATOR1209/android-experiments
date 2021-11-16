package ua.alegator1209.feature_repositories.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
internal interface RepositoryDao {
    @Query("SELECT * FROM repositories")
    fun getAllRepos(): Single<List<RepositoryEntity>>

    @Query("SELECT * FROM repositories LIMIT :perPage OFFSET :page * :perPage")
    fun getRepositories(perPage: Int, page: Int): Single<List<RepositoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepositories(repositories: List<RepositoryEntity>): Completable

    @Query("SELECT * FROM contributors WHERE repository_id = :repositoryId")
    fun getContributors(repositoryId: Int): Single<List<ContributorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContributors(contributors: List<ContributorEntity>): Completable

    @Query("SELECT * FROM languages WHERE repository_id = :repositoryId")
    fun getLanguages(repositoryId: Int): Single<List<LanguageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLanguages(languages: List<LanguageEntity>): Completable

    @Query("SELECT * FROM topics WHERE repository_id = :repositoryId")
    fun getTopics(repositoryId: Int): Single<TopicsRecordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopics(record: TopicsRecordEntity): Completable
}
