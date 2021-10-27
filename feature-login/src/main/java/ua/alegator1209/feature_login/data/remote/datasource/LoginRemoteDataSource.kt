package ua.alegator1209.feature_login.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_login.core.datasource.LoginDataSource
import ua.alegator1209.feature_login.data.remote.api.LoginApi
import ua.alegator1209.feature_login.data.remote.mappers.toUser
import ua.alegator1209.feature_login.data.remote.model.UserDto

internal class LoginRemoteDataSource(
    private val api: LoginApi
) : LoginDataSource {
    override fun login(token: String): Single<User> {
        return api.getUserInfo("$token token").map(UserDto::toUser)
    }
}
