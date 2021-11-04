package ua.alegator1209.feature_repositories.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_repositories.core.domain.interactors.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class RepositoriesViewModel : ViewModel() {
    @Inject
    internal lateinit var useCase: GetRepositoriesUseCase

    @Inject
    internal lateinit var user: User

    internal val pageSize: Int get() = useCase.PAGE_SIZE
    private val loading = AtomicBoolean(false)

    internal fun loadRepositories(): Single<List<Repository>>? {
        return if (loading.compareAndSet(false, true)) {
            useCase()
                .retry(2)
                .doFinally {
                    loading.set(false)
                }
        } else {
            null
        }
    }
}
