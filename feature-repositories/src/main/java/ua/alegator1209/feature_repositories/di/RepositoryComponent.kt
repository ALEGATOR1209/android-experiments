package ua.alegator1209.feature_repositories.di

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.alegator1209.core.di.PerFeature
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core_ui.BaseComponent
import ua.alegator1209.data.remote.common.api
import ua.alegator1209.feature_repositories.core.datasource.RepositoriesDataSource
import ua.alegator1209.feature_repositories.core.domain.interaction.GetRepositoriesUseCase
import ua.alegator1209.feature_repositories.data.remote.api.RepositoriesApi
import ua.alegator1209.feature_repositories.data.remote.datasource.RepositoriesRemoteDataSource
import ua.alegator1209.feature_repositories.ui.RepositoriesViewModel

@PerFeature
@Component(modules = [RepositoryModule::class], dependencies = [BaseComponent::class])
interface RepositoryComponent {
    fun inject(viewModel: RepositoriesViewModel)
}

interface RepositoryComponentProvider {
    fun provideRepositoryComponent(): RepositoryComponent
}

@Module
class RepositoryModule {
    @Provides
    @PerFeature
    internal fun provideUseCase(ds: RepositoriesDataSource) = GetRepositoriesUseCase(ds)

    @Provides
    @PerFeature
    internal fun provideDataSource(user: User, api: RepositoriesApi): RepositoriesDataSource {
        return RepositoriesRemoteDataSource(api, user.name)
    }

    @Provides
    @PerFeature
    internal fun provideApi(retrofit: Retrofit): RepositoriesApi = retrofit.api()
}
