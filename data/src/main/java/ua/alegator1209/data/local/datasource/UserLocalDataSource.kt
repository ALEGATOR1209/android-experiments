package ua.alegator1209.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User

internal class UserLocalDataSource : UserDataSource {
    private var user: User? = null

    override fun getUser(): Single<User> = user
        ?.let { Single.just(it) }
        ?: Single.error(NoSuchElementException())

    override fun saveUser(user: User): Completable {
        this.user = user
        return Completable.complete()
    }

    override fun deleteUser(): Completable {
        user = null
        return Completable.complete()
    }
}
