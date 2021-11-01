package ua.alegator1209.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ua.alegator1209.data.model.UserDto

internal interface UserApi {
    @GET("/user")
    fun getUserInfo(): Single<UserDto>

    @GET("/user")
    fun getUserInfo(
        @Header(value = "Authorization") token: String,
    ): Single<UserDto>
}
