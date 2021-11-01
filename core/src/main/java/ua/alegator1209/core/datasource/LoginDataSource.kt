package ua.alegator1209.core.datasource

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.domain.model.User

interface LoginDataSource {
    fun login(token: String): Single<User>
}
