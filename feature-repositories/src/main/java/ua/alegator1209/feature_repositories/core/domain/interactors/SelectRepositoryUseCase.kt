package ua.alegator1209.feature_repositories.core.domain.interactors

import ua.alegator1209.feature_repositories.core.domain.model.Repository

internal class SelectRepositoryUseCase {
    var selectedRepository: Repository? = null
        private set

    operator fun invoke(repository: Repository) {
        selectedRepository = repository
    }
}
