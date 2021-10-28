package ua.alegator1209.core.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User

interface UserDataSource {
    fun getUser(): Single<User>
    fun getToken(): Single<String>
    fun saveToken(token: String): Completable
    fun saveUser(user: User): Completable

    fun clearToken(): Completable
    fun clearUser(): Completable
}
