package ua.alegator1209.feature_profile.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ua.alegator1209.data.model.UserDto

interface GitHubUserApi {
    @GET("/user")
    fun getUserInfo(): Single<UserDto>
}
