package com.rootstrap.donations.repository.connection

import com.rootstrap.donations.repository.services.HeadersInterceptor
import com.rootstrap.donations.utils.API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectionProvider {

    private fun build(): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(HeadersInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    fun <T> create(klass: Class<T>): T {
        return build().create(klass)
    }
}
