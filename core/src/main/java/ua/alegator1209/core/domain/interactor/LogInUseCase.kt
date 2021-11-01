package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.datasource.LoginDataSource
import ua.alegator1209.core.datasource.UserPersistentDataSource
import ua.alegator1209.core.domain.repository.TokenRepository

class LogInUseCase(
    private val userDataSource: UserPersistentDataSource,
    private val tokenRepository: TokenRepository,
    private val loginDataSource: LoginDataSource,
) {
    operator fun invoke(token: String): Completable = loginDataSource.login(token)
        .flatMapCompletable { user ->
            Completable.defer {
                tokenRepository.saveToken(token).andThen(
                    Completable.defer {
                        userDataSource.saveUser(user)
                    }
                )
            }
        }
}
