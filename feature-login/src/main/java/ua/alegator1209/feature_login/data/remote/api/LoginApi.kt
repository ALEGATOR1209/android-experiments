package ua.alegator1209.feature_login.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ua.alegator1209.feature_login.data.remote.model.UserDto

internal interface LoginApi {
    @GET("/user")
    fun getUserInfo(
        @Header(value = "Authorization") token: String,
    ): Single<UserDto>
}
