package ua.alegator1209.feature_repositories.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.datasource.LanguagesDataSource
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.core.domain.model.owner
import ua.alegator1209.feature_repositories.data.mappers.toLanguages
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi

internal class LanguagesRemoteDataSource(
    private val api: RepositoriesApi
) : LanguagesDataSource {
    override fun get(repository: Repository): Single<List<Language>> {
        return api.getLanguages(repository.owner, repository.name).map { langMap ->
            langMap.toLanguages()
        }
    }
}
