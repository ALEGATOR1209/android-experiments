package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.repository.TokenRepository

class LogOutUseCase(
    private val userDataSource: UserDataSource,
    private val tokenRepository: TokenRepository,
) {
    operator fun invoke(): Completable = tokenRepository.deleteToken()
        .andThen(Completable.defer { userDataSource.deleteUser() })
}
