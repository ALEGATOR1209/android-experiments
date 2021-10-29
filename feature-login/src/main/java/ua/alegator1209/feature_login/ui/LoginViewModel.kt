package ua.alegator1209.feature_login.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.interactor.LogInUseCase
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    var token: String = ""

    @Inject lateinit var logInUseCase: LogInUseCase

    fun login(): Completable {
        val t = token
        if (t.isBlank()) {
            return Completable.error(
                IllegalArgumentException("Username & password must not be blank")
            )
        }

        return logInUseCase(t)
    }
}
