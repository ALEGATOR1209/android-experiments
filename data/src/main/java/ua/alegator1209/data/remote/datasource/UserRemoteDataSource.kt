package ua.alegator1209.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.mappers.toUser
import ua.alegator1209.data.model.UserDto
import ua.alegator1209.data.remote.api.UserApi

internal class UserRemoteDataSource(
    private val api: UserApi
) : UserDataSource {
    override fun getUser(): Single<User> = api.getUserInfo().map(UserDto::toUser)
}
