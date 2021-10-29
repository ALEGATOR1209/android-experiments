package ua.alegator1209.core.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TokenDataSource {
    fun getToken(): Single<String>
    fun saveToken(token: String): Completable
    fun clearToken(): Completable
}
