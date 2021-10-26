package ua.alegator1209.feature_login.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface LoginApi {
    @GET("/users/{username}")
    fun getUserInfo(@Path(value = "username") username: String): Single<Unit>
}
