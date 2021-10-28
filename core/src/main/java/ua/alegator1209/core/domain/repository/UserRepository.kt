package ua.alegator1209.core.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User

class UserRepository(private val dataSource: UserDataSource) {
    private var token: String? = null

    fun getUser(): Single<User> = dataSource.getUser()

    fun getToken(): Single<String> = token?.let { Single.just(it) }
        ?: dataSource.getToken().doOnSuccess {
            synchronized(this) { token = it }
        }

    fun logIn(user: User, token: String): Completable {
        synchronized(this) {
            this.token = token
        }

        return dataSource
            .saveToken(token)
            .andThen(Completable.defer { dataSource.saveUser(user) })
    }

    fun logOut(): Completable {
        token = null

        return dataSource
            .clearToken()
            .andThen { dataSource.clearUser() }
    }
}
