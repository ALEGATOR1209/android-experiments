package ua.alegator1209.feature_profile.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.interactor.GetUserUseCase
import ua.alegator1209.core.domain.interactor.LogOutUseCase
import ua.alegator1209.core.domain.model.User
import javax.inject.Inject

class ProfileViewModel : ViewModel() {
    @Inject lateinit var getUserUseCase: GetUserUseCase
    @Inject lateinit var logOutUseCase: LogOutUseCase

    fun loadUserData(): Single<User> = getUserUseCase()
    fun logout(): Completable = logOutUseCase()
}
