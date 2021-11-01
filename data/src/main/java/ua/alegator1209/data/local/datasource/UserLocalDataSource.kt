package ua.alegator1209.data.local.datasource

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ua.alegator1209.core.datasource.UserPersistentDataSource
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.data.mappers.toDto
import ua.alegator1209.data.mappers.toUser
import ua.alegator1209.data.model.UserDto
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception
import java.nio.charset.StandardCharsets

internal class UserLocalDataSource(
    private val context: Context,
    private val json: Json,
) : UserPersistentDataSource {
    private var user: User? = null
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private val filename = "user"
    private val file get() = File(context.filesDir, filename)
    private val encryptedFile: EncryptedFile
        get() = EncryptedFile.Builder(
            file,
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

    override fun getUser(): Single<User> = user
        ?.let { Single.just(it) }
        ?: try {
            Single.just(readUserFromFile())
        } catch (e: Exception) {
            Single.error(e)
        }

    private fun readUserFromFile(): User {
        val inputStream = encryptedFile.openFileInput()
        val byteArrayOutputStream = ByteArrayOutputStream()

        var nextByte = inputStream.read()
        while (nextByte != -1) {
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }

        val plainText = byteArrayOutputStream.toByteArray().toString(StandardCharsets.UTF_8)
        val dto: UserDto = json.decodeFromString(plainText)

        return dto.toUser()
    }

    override fun saveUser(user: User): Completable = try {
        writeUserToFile(user)
        this.user = user
        Completable.complete()
    } catch (e: Exception) {
        Completable.error(e)
    }

    private fun writeUserToFile(user: User) {
        if (file.exists()) file.delete()
        val serialized = json.encodeToString(user.toDto())
        encryptedFile.openFileOutput().use {
            it.write(serialized.toByteArray(StandardCharsets.UTF_8))
            it.flush()
        }
    }

    override fun deleteUser(): Completable {
        user = null
        return Completable.complete()
    }
}
