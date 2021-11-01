package ua.alegator1209.core.domain.interactor

import io.reactivex.rxjava3.core.Observable
import ua.alegator1209.core.datasource.UserDataSource
import ua.alegator1209.core.datasource.UserPersistentDataSource
import ua.alegator1209.core.domain.model.User

class GetUserUseCase(
    private val remote: UserDataSource,
    private val local: UserPersistentDataSource,
) {
    operator fun invoke(): Observable<User> = local.getUser()
        .toObservable()
        .mergeWith(
            Observable.defer {
                remote.getUser()
                    .doOnError(Throwable::printStackTrace)
                    .doOnSuccess(local::saveUser)
                    .toObservable()
            }
        )
}
