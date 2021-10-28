package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.repository.UserRepository

class GetTokenUseCase(private val repository: UserRepository) {
    operator fun invoke(): Single<String> = repository.getToken()
}
