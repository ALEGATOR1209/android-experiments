package ua.alegator1209.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User

internal class UserLocalDataSource : UserDataSource {
    private var user: User? = null
    private var token: String? = null

    override fun getUser(): Single<User> = user
        ?.let { Single.just(it) }
        ?: Single.error(NoSuchElementException())

    override fun getToken(): Single<String> = token
        ?.let { Single.just(it) }
        ?: Single.error(NoSuchElementException())

    override fun saveToken(token: String): Completable {
        this.token = token
        return Completable.complete()
    }

    override fun saveUser(user: User): Completable {
        this.user = user
        return Completable.complete()
    }

    override fun clearToken(): Completable {
        token = null
        return Completable.complete()
    }

    override fun clearUser(): Completable {
        user = null
        return Completable.complete()
    }
}
