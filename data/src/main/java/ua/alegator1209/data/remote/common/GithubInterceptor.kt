package ua.alegator1209.data.remote.common

import okhttp3.Interceptor
import okhttp3.Response

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request()
        .newBuilder()
        .addHeader("Accept", "application/vnd.github.v3+json")
        .build()
        .let(chain::proceed)
}
