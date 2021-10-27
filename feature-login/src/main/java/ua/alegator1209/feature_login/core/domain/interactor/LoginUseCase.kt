package ua.alegator1209.feature_login.core.domain.interactor

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_login.core.datasource.LoginDataSource

interface LoginUseCase {
    operator fun invoke(token: String): Single<User>
}

internal class LoginUseCaseImpl(private val dataSource: LoginDataSource) : LoginUseCase {
    override fun invoke(token: String) = dataSource.login(token)
}
