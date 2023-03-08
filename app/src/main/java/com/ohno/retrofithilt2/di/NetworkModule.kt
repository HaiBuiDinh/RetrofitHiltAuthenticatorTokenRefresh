package com.ohno.retrofithilt2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ohno.retrofithilt2.data.remote.retrofit.AuthApiService
import com.ohno.retrofithilt2.data.remote.retrofit.MainApiService
import com.ohno.retrofithilt2.util.AuthAuthenticator
import com.ohno.retrofithilt2.util.AuthInterceptor
import com.ohno.retrofithilt2.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.math.log

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonConverterFactory: GsonConverterFactory): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl("https://jwt-test-api.onrender.com/api/")
            .addConverterFactory(gsonConverterFactory)

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthApiService =
        retrofit.build().create(AuthApiService::class.java)

//    @Singleton
//    @Provides
//    fun provideRetrofitInstance(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://dummy.restapiexample.com/api/v1/")
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }

    @Singleton
    @Provides
    fun provideMainAPIService(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): MainApiService =
        retrofit.client(okHttpClient)
            .build()
            .create(MainApiService::class.java)

}