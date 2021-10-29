package ua.alegator1209.android_experiments

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.interactor.GetTokenUseCase
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject lateinit var getTokenUseCase: GetTokenUseCase

    fun checkToken(): Single<Boolean> {
        return getTokenUseCase()
            .onErrorReturn { "" }
            .map(String::isNotBlank)
    }
}
