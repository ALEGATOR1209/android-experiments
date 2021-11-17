package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface TopicsCachingDataSource : TopicsDataSource {
    fun saveTopics(repository: Repository, topics: List<String>): Completable
}
