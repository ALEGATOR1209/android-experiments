package ua.alegator1209.feature_login.core.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User

internal interface LoginDataSource {
    fun login(token: String): Single<User>
}
