package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core.domain.repository.UserRepository

class LogInUseCase(private val repository: UserRepository) {
    operator fun invoke(user: User, token: String): Completable = repository.logIn(user, token)
}
