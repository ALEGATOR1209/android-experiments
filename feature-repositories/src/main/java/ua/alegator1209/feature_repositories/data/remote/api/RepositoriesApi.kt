package ua.alegator1209.feature_repositories.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.alegator1209.feature_repositories.data.remote.model.RepositoryDto

internal interface RepositoriesApi {
    @GET("user/repos")
    fun getRepos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<List<RepositoryDto>>
}
