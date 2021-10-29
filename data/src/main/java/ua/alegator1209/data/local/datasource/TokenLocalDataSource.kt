package ua.alegator1209.data.local.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.TokenDataSource

internal class TokenLocalDataSource : TokenDataSource {
    private var token: String? = null

    override fun getToken(): Single<String> = token
        ?.let { Single.just(it) }
        ?: Single.error(NoSuchElementException())

    override fun saveToken(token: String): Completable {
        this.token = token
        return Completable.complete()
    }

    override fun clearToken(): Completable {
        token = null
        return Completable.complete()
    }
}
