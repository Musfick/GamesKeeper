package com.foxhole.gameskeeper.di

import com.foxhole.gameskeeper.datasource.ExplorePagingSource
import com.foxhole.gameskeeper.local.room.GameDao
import com.foxhole.gameskeeper.remote.api.RawgApi
import com.foxhole.gameskeeper.repositories.singleGame.SingleGameRepo
import com.foxhole.gameskeeper.repositories.singleGame.SingleGameRepoImpl
import com.foxhole.gameskeeper.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder().also {
        it.addInterceptor(interceptor)
        it.connectTimeout(60, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun providesRetrofitClient(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder().also {
        it.baseUrl(BASE_URL)
        it.client(client)
        it.addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun providesRawgApi(retrofit: Retrofit): RawgApi = retrofit.create(RawgApi::class.java)

    @Provides
    @Singleton
    fun providesExplorePagingSource(rawgApi: RawgApi) = ExplorePagingSource(rawgApi)

    @Provides
    @Singleton
    fun providesSingleGameRepo(rawgApi: RawgApi, gameDao: GameDao): SingleGameRepo = SingleGameRepoImpl(rawgApi, gameDao)
}