package ua.alegator1209.feature_profile.data.remote.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.mappers.toUser
import ua.alegator1209.data.model.UserDto
import ua.alegator1209.feature_profile.data.remote.api.GitHubUserApi

internal class UserRemoteDataSource(
    private val api: GitHubUserApi
) : UserDataSource {
    override fun getUser(): Single<User> = api.getUserInfo().map(UserDto::toUser)
    override fun saveUser(user: User): Completable = error("Not supported")
    override fun deleteUser(): Completable = error("Not supported")
}
