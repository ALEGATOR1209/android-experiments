package ua.alegator1209.data.remote.common

import okhttp3.Interceptor
import okhttp3.Response
import ua.alegator1209.core.domain.interactor.GetTokenUseCase

class AuthInterceptor(
    private val useCase: GetTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = useCase().blockingGet()

        return chain.request()
            .newBuilder()
            .addHeader("Authorization", "token $token")
            .build()
            .let(chain::proceed)
    }
}
