package ua.alegator1209.feature_login.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_login.core.datasource.LoginDataSource
import ua.alegator1209.feature_login.core.domain.model.LoginCredentials

interface LoginUseCase {
    operator fun invoke(credentials: LoginCredentials): Completable
}

class LoginUseCaseImpl(private val dataSource: LoginDataSource) : LoginUseCase {
    override fun invoke(credentials: LoginCredentials) = dataSource.login(credentials)
}
