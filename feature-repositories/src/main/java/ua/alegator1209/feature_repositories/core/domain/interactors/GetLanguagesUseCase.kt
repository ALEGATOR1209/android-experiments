package ua.alegator1209.feature_repositories.core.domain.interactors

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.LanguagesCachingDataSource
import ua.alegator1209.feature_repositories.core.datasource.LanguagesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class GetLanguagesUseCase(
    private val local: LanguagesCachingDataSource,
    private val remote: LanguagesDataSource
) {
    operator fun invoke(repository: Repository): Single<List<Language>> {
        return remote[repository]
            .retry(2)
            .doOnSuccess { cache(it, repository) }
            .onErrorResumeWith(
                Single.defer {
                    local[repository]
                }
            )
    }

    private fun cache(languages: List<Language>, repository: Repository) {
        local.save(languages, repository)
            .subscribe({}, Throwable::printStackTrace)
    }
}
