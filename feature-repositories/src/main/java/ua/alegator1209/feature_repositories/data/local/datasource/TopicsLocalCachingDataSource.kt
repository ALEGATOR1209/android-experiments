package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.TopicsCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.local.db.TopicsRecordEntity

internal class TopicsLocalCachingDataSource(
    private val dao: RepositoryDao,
) : TopicsCachingDataSource {
    override fun saveTopics(repository: Repository, topics: List<String>): Completable {
        return dao.saveTopics(
            TopicsRecordEntity(
                repositoryId = repository.id,
                topics = topics.serialize()
            )
        )
    }

    override fun getTopics(repository: Repository): Single<List<String>> {
        return dao.getTopics(repository.id)
            .map { it.deserialize() }
    }

    private fun List<String>.serialize() = joinToString(";")
    private fun TopicsRecordEntity.deserialize() = topics.split(";")
}
