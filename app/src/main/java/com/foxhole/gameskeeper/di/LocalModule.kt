package com.foxhole.gameskeeper.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.foxhole.gameskeeper.local.datastore.CachePreferences
import com.foxhole.gameskeeper.local.datastore.CachePreferencesImpl
import com.foxhole.gameskeeper.local.room.AppDatabase
import com.foxhole.gameskeeper.utils.Constants.APP_CACHE_DATA_STORE
import com.foxhole.gameskeeper.utils.Constants.APP_DATABASE_NAME
import com.foxhole.gameskeeper.utils.ResponseHandler
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
object LocalModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        APP_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesGameDao(appDatabase: AppDatabase) = appDatabase.gameDao()

    @Provides
    @Singleton
    fun providesDataStore(
            @ApplicationContext app: Context
    ): DataStore<Preferences> = app.createDataStore(APP_CACHE_DATA_STORE)

    @Provides
    @Singleton
    fun providesResponseHandler() = ResponseHandler()

    @Provides
    @Singleton
    fun providesCachePreference(dataStore: DataStore<Preferences>): CachePreferences = CachePreferencesImpl(dataStore)


}