package ua.alegator1209.feature_repositories.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.feature_repositories.core.domain.interactors.GetBranchesUseCase
import ua.alegator1209.feature_repositories.core.domain.interactors.SelectRepositoryUseCase
import ua.alegator1209.feature_repositories.core.domain.model.Branch
import ua.alegator1209.feature_repositories.core.domain.model.Repository
import javax.inject.Inject

class BranchesViewModel : ViewModel() {
    @Inject internal lateinit var selectRepositoryUseCase: SelectRepositoryUseCase
    @Inject internal lateinit var getBranchesUseCase: GetBranchesUseCase

    internal val repository: Single<Repository>
        get() = selectRepositoryUseCase.selectedRepository?.let {
            Single.just(it)
        } ?: Single.error(IllegalStateException("Repository not selected"))

    internal fun getBranches(): Single<List<Branch>> {
        return repository.concatMap { getBranchesUseCase(it) }
    }
}
