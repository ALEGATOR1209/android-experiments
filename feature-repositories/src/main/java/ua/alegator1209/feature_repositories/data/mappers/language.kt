package ua.alegator1209.feature_repositories.data.mappers

import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.data.local.db.LanguageEntity

internal fun Language.toEntity(repository: Repository) = LanguageEntity(
    language = name,
    byteCount = byteCount,
    repositoryId = repository.id
)

internal fun LanguageEntity.toLanguage() = Language(name = language, byteCount,)
internal fun Map<String, Int>.toLanguages() = map { (name, byteCount) ->
    Language(name, byteCount)
}
