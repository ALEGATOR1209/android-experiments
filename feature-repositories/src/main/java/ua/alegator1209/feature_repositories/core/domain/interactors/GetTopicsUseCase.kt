package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.TopicsCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.TopicsDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class GetTopicsUseCase(
    private val local: TopicsCachingDataSource,
    private val remote: TopicsDataSource,
) {
    operator fun invoke(repository: Repository): Single<List<String>> {
        return remote
            .getTopics(repository)
            .doOnSuccess {
                local.saveTopics(repository, it)
                    .subscribe({}, Throwable::printStackTrace)
            }.onErrorResumeWith(Single.defer { local.getTopics(repository) })
    }
}
