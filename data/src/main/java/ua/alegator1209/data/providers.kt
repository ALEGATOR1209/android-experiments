package ua.alegator1209.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

internal fun chucker(context: Context) = ChuckerInterceptor.Builder(context)
    .redactHeaders("Authorization")
    .alwaysReadResponseBody(true)
    .build()

internal fun client(context: Context) = OkHttpClient.Builder()
    .addInterceptor(chucker(context))
    .build()

@ExperimentalSerializationApi
internal fun retrofit(baseUrl: String, client: OkHttpClient) = Retrofit.Builder()
    .client(client)
    .baseUrl(baseUrl)
    .addConverterFactory(Json.asConverterFactory(
        MediaType.parse("application/json") ?: error("Invalid type")
    ))
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

inline fun <reified T> Retrofit.api() = create(T::class.java)
