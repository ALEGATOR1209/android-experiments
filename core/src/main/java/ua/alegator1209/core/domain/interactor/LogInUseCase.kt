package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core.domain.repository.TokenRepository

class LogInUseCase(
    private val userDataSource: UserDataSource,
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(user: User, token: String): Completable = tokenRepository
        .saveToken(token)
        .andThen(
            Completable.defer {
                userDataSource.saveUser(user)
            }
        )
}
