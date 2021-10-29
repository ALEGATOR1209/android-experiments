package ua.alegator1209.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.LoginDataSource
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.mappers.toUser
import ua.alegator1209.data.model.UserDto
import ua.alegator1209.data.remote.api.UserApi

internal class LoginRemoteDataSource(
    private val api: UserApi
) : LoginDataSource {
    override fun login(token: String): Single<User> = api.getUserInfo("token $token")
        .map(UserDto::toUser)
}
