package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.repository.UserRepository

class LogOutUseCase(private val repository: UserRepository) {
    operator fun invoke(): Completable = repository.logOut()
}
