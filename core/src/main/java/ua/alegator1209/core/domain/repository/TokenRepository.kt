package ua.alegator1209.core.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.TokenDataSource

class TokenRepository(private val dataSource: TokenDataSource) {
    private var token: String? = null

    fun getToken(): Single<String> = token?.let { Single.just(it) }
        ?: dataSource.getToken().doOnSuccess {
            synchronized(this) { token = it }
        }

    fun saveToken(token: String): Completable {
        synchronized(this) { this.token = token }
        return dataSource.saveToken(token)
    }

    fun deleteToken(): Completable {
        token = null
        return dataSource.clearToken()
    }
}
