package ua.alegator1209.feature_repositories.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.domain.interaction.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {
    @Inject
    internal lateinit var useCase: GetRepositoriesUseCase

    @Inject
    internal lateinit var user: User

    internal fun loadRepositories(): Single<List<Repository>> = useCase()
}
