package ua.alegator1209.data.local.datasource

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ua.alegator1209.core.datasource.TokenDataSource
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

internal class TokenLocalDataSource(
    private val context: Context
) : TokenDataSource {
    private var token: String? = null
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private val filename = "token"
    private val file get() = File(context.filesDir, filename)
    private val encryptedFile: EncryptedFile get() = EncryptedFile.Builder(
        file,
        context,
        mainKeyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    override fun getToken(): Single<String> = token
        ?.let { Single.just(it) }
        ?: try {
            readTokenFromFile()
        } catch (e: Exception) {
            Single.error(e)
        }

    private fun readTokenFromFile(): Single<String> {
        val inputStream = encryptedFile.openFileInput()
        val byteArrayOutputStream = ByteArrayOutputStream()
        var nextByte = inputStream.read()
        while (nextByte != -1) {
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }

        val plainText = byteArrayOutputStream.toByteArray()

        return Single.just(plainText.toString(StandardCharsets.UTF_8))
    }

    override fun saveToken(token: String): Completable {
        this.token = token
        return try {
            writeTokenToFile(token)
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    private fun writeTokenToFile(token: String): Completable {
        if (file.exists()) file.delete()
        encryptedFile.openFileOutput().use {
            it.write(token.toByteArray(StandardCharsets.UTF_8))
            it.flush()
        }

        return Completable.complete()
    }

    override fun clearToken(): Completable {
        token = null
        file.delete()
        return Completable.complete()
    }
}
