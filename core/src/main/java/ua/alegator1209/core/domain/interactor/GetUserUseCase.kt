package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.domain.model.User

class GetUserUseCase(private val ds: UserDataSource) {
    operator fun invoke(): Single<User> = ds.getUser()
}
