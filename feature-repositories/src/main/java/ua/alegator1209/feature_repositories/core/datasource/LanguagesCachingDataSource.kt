package ua.alegator1209.feature_repositories.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal interface LanguagesCachingDataSource : LanguagesDataSource {
    fun save(languages: List<Language>, repository: Repository): Completable
}
