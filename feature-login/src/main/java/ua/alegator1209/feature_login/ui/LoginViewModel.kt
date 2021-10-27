package ua.alegator1209.feature_login.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.feature_login.core.domain.interactor.LoginUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    var token: String = ""

    @Inject
    lateinit var useCase: LoginUseCase

    fun login(): Single<User> {
        val t = token
        if (t.isBlank()) {
            return Single.error(IllegalArgumentException("Username & password must not be blank"))
        }

        return useCase(t).timeout(3L, TimeUnit.SECONDS)
    }
}
