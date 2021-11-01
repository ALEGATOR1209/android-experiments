package ua.alegator1209.core.datasource

import io.reactivex.rxjava3.core.Completable
import ua.alegator1209.core.domain.model.User

interface UserPersistentDataSource : UserDataSource {
    fun saveUser(user: User): Completable
    fun deleteUser(): Completable
}
