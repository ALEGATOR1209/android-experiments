package ua.alegator1209.feature_repositories.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.domain.interactors.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.SelectRepositoryUseCase
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.routing.RepositoryPhase
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class RepositoryViewModel : ViewModel() {
    @Inject
    internal lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Inject
    internal lateinit var selectRepositoryUseCase: SelectRepositoryUseCase

    @Inject
    internal lateinit var user: User

    internal val backStack = mutableListOf<RepositoryPhase>()

    internal val pageSize: Int get() = getRepositoriesUseCase.PAGE_SIZE
    private val loading = AtomicBoolean(false)

    internal fun loadRepositories(page: Int): Single<List<Repository>>? {
        return if (loading.compareAndSet(false, true)) {
            getRepositoriesUseCase(page).doFinally {
                loading.set(false)
            }
        } else {
            null
        }
    }

    internal fun selectRepository(repository: Repository) {
        selectRepositoryUseCase(repository)
    }

    internal fun selectedRepositoryInfo(): Single<Repository> {
        return selectRepositoryUseCase.selectedRepository
            ?.let { Single.just(it) }
            ?: Single.error(IllegalStateException("No selected repository"))
    }
}
