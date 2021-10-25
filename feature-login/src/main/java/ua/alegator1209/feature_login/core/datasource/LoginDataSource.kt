package ua.alegator1209.feature_login.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.feature_login.core.domain.model.LoginCredentials

interface LoginDataSource {
    fun login(credentials: LoginCredentials): Completable
}
