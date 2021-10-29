package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.repository.TokenRepository

class GetTokenUseCase(private val repository: TokenRepository) {
    operator fun invoke(): Single<String> = repository.getToken()
}
