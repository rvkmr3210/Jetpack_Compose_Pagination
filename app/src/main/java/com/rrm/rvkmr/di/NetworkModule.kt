package com.rrm.rvkmr.di

import com.rrm.rvkmr.retrofit.ApiEndPoint
import com.rrm.rvkmr.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    companion object {
        private const val READ_TIMEOUT = 20L
        private const val CONNECT_TIMEOUT = 5L
    }

    //This client is sync and used independently of the app client
    //Tasks. Token refresh, Cart error resolver,
    @Provides
    @Singleton
    fun provideInterceptorOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }


    @Provides
    @Singleton
    fun provideApiService(
        oKHttpClient: OkHttpClient,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiEndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oKHttpClient)
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}