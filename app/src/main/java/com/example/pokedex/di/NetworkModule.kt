package com.example.pokedex.di

import android.content.Context
import com.example.pokedex.BuildConfig
import com.example.pokedex.data.remote.api.PokemonApi
import com.example.pokedex.data.remote.config.NetworkInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() = BuildConfig.BASE_API_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BASE_URL") baseUrl: String, okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient()
            .newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            httpBuilder.addInterceptor(loggingInterceptor)
        }

        return httpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(
        @ApplicationContext appContext: Context
    ) = NetworkInterceptor(appContext)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    // APIs

    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)
}