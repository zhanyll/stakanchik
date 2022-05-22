package com.example.stakanchik.di

import com.example.stakanchik.BuildConfig
import com.example.stakanchik.data.network.ArticlesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideArticleApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = createOkHttpClientBuilder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = createRetrofit(okHttpClient)

    private fun createOkHttpClientBuilder(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        return OkHttpClient.Builder()
            .apply {
                connectTimeout(3, TimeUnit.MINUTES)
                writeTimeout(3, TimeUnit.MINUTES)
                readTimeout(3, TimeUnit.MINUTES)
                if (BuildConfig.DEBUG) {
                    addInterceptor(interceptor)
                }
            }
    }

    private fun createRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}