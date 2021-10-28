package ua.alegator1209.feature_login.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.interactor.GetUserUseCase
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_login.core.domain.interactor.GitHubLoginUseCase
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    var token: String = ""

    @Inject
    lateinit var logInUseCase: GitHubLoginUseCase

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    fun login(): Single<User> {
        val t = token
        if (t.isBlank()) {
            return Single.error(IllegalArgumentException("Username & password must not be blank"))
        }

        return logInUseCase(t).andThen(Single.defer { getUserUseCase() })
    }
}
