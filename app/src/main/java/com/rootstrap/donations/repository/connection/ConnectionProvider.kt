package com.rootstrap.donations.repository.connection

import android.content.Context
import com.rootstrap.donations.utils.API_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectionProvider {

    private fun build(context: Context): Retrofit {
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

    fun <T> create(context: Context, klass: Class<T>): T {
        return build(context).create(klass)
    }

}
