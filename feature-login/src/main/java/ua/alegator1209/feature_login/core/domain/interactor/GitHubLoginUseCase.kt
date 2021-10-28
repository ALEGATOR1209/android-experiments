package ua.alegator1209.feature_login.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.interactor.LogInUseCase
import ua.alegator1209.feature_login.core.datasource.LoginDataSource

interface GitHubLoginUseCase {
    operator fun invoke(token: String): Completable
}

internal class GitHubLoginUseCaseImpl(
    private val dataSource: LoginDataSource,
    private val logInUseCase: LogInUseCase
) : GitHubLoginUseCase {
    override fun invoke(token: String): Completable = dataSource.login(token)
        .flatMapCompletable { logInUseCase(it, token) }
}
