package ua.alegator1209.feature_login.data.remote.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_login.core.datasource.LoginDataSource
import ua.alegator1209.feature_login.core.domain.model.LoginCredentials
import ua.alegator1209.feature_login.data.remote.api.LoginApi

internal class LoginRemoteDataSource(
    private val api: LoginApi
) : LoginDataSource {
    override fun login(credentials: LoginCredentials): Completable {
        return api.getUserInfo(credentials.username).ignoreElement()
    }
}
