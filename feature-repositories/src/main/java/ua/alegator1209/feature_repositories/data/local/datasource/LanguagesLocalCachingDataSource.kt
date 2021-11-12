package ua.alegator1209.feature_repositories.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.LanguagesCachingDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.LanguageEntity
import ua.alegator1209.feature_repositories.data.local.db.RepositoryDao
import ua.alegator1209.feature_repositories.data.mappers.toEntity
import ua.alegator1209.feature_repositories.data.mappers.toLanguage

internal class LanguagesLocalCachingDataSource(
    private val dao: RepositoryDao,
) : LanguagesCachingDataSource {
    override fun save(languages: List<Language>, repository: Repository): Completable {
        return dao.saveLanguages(languages.map { it.toEntity(repository) })
    }

    override fun get(repository: Repository): Single<List<Language>> {
        return dao.getLanguages(repository.id).map { languages ->
            languages.map(LanguageEntity::toLanguage)
        }
    }
}
