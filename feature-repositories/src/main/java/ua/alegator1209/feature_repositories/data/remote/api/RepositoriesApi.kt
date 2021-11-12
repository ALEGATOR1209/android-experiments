package ua.alegator1209.feature_repositories.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.alegator1209.feature_repositories.data.remote.model.ContributorDto
import ua.alegator1209.feature_repositories.data.remote.model.RepositoryDto

internal interface RepositoriesApi {
    @GET("user/repos")
    fun getRepos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String = "full_name",
    ): Single<List<RepositoryDto>>

    @GET("repos/{owner}/{repo}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Single<List<ContributorDto>>

    @GET("/repos/{owner}/{repo}/languages")
    fun getLanguages(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Single<Map<String, Int>>
}
