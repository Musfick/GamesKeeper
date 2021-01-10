package com.foxhole.gameskeeper.di

import android.content.Context
import androidx.room.Room
import com.foxhole.gameskeeper.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(
        @ApplicationContext app: Context
    ) = Room.inMemoryDatabaseBuilder(
        app,
        AppDatabase::class.java
    ).allowMainThreadQueries().build()
}