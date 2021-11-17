package ua.alegator1209.feature_repositories.di

import dagger.Component
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core_ui.di.BaseComponent
import ua.alegator1209.feature_repositories.ui.BranchesViewModel
import ua.alegator1209.feature_repositories.ui.RepositoryViewModel

@PerFeature
@Component(modules = [RepositoryModule::class], dependencies = [BaseComponent::class])
interface RepositoryComponent {
    fun inject(viewModel: RepositoryViewModel)
    fun inject(viewModel: BranchesViewModel)
}

interface RepositoryComponentProvider {
    fun provideRepositoryComponent(): RepositoryComponent
}
