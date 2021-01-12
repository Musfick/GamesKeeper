package com.foxhole.gameskeeper.di

import android.content.Context
import com.foxhole.gameskeeper.adapter.FavoriteGameAdapter
import com.foxhole.gameskeeper.adapter.GameAdapter
import com.foxhole.gameskeeper.datasource.ExplorePagingSource
import com.foxhole.gameskeeper.local.room.GameDao
import com.foxhole.gameskeeper.remote.api.RawgApi
import com.foxhole.gameskeeper.repositories.main.MainRepo
import com.foxhole.gameskeeper.repositories.main.MainRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesMainRepository(
            gameDao: GameDao,
            rawgApi: RawgApi
    ): MainRepo = MainRepoImpl(gameDao, rawgApi)

    @Provides
    @Singleton
    fun providesGameAdapter(
            @ApplicationContext app: Context
    ): GameAdapter = GameAdapter(app)

    @Provides
    @Singleton
    fun providesFavoriteGameAdapter() = FavoriteGameAdapter()
}