package ua.alegator1209.feature_repositories.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.domain.interactors.GetContributorsUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.GetLanguagesUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.GetTopicsUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.SelectRepositoryUseCase
import ua.alegator1209.feature_repositories.core.domain.model.Contributor
import ua.alegator1209.feature_repositories.core.domain.model.Language
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import ua.alegator1209.feature_repositories.routing.RepositoryPhase
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.IllegalStateException

class RepositoryViewModel : ViewModel() {
    @Inject
    internal lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Inject
    internal lateinit var selectRepositoryUseCase: SelectRepositoryUseCase

    @Inject
    internal lateinit var getContributorsUseCase: GetContributorsUseCase

    @Inject
    internal lateinit var languagesUseCase: GetLanguagesUseCase

    @Inject
    internal lateinit var topicsUseCase: GetTopicsUseCase

    @Inject
    internal lateinit var user: User

    internal val selectedRepository: Single<Repository> get() = selectRepositoryUseCase
        .selectedRepository
        ?.let { Single.just(it) }
        ?: Single.error(IllegalStateException("Repository not selected"))

    internal val backStack = mutableListOf<RepositoryPhase>()

    internal val pageSize: Int get() = getRepositoriesUseCase.PAGE_SIZE
    private val loading = AtomicBoolean(false)

    internal fun loadRepositories(page: Int, fromIndex: Int): Single<List<Repository>>? {
        return if (loading.compareAndSet(false, true)) {
            getRepositoriesUseCase(page, fromIndex).doFinally {
                loading.set(false)
            }
        } else {
            null
        }
    }

    internal fun selectRepository(repository: Repository) {
        selectRepositoryUseCase(repository)
    }

    internal fun getContributorsForSelectedRepository(): Flowable<List<Contributor>> {
        return selectedRepository.toFlowable().flatMap { getContributorsUseCase(it) }
    }

    internal fun getLanguagesForSelectedRepository(): Single<List<Language>> {
        return selectedRepository.flatMap { languagesUseCase(it) }
    }

    internal fun getTopicsForSelectedRepository(): Single<List<String>> {
        return selectedRepository.flatMap { topicsUseCase(it) }
    }
}
