package com.foxhole.gameskeeper.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import com.foxhole.gameskeeper.model.Game
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Musfick Jamil on 1/12/2021$.
 */

class CachePreferencesImpl(private val dataStore: DataStore<Preferences>) : CachePreferences {

    var isReadFromCache = false
    override fun isReadFromCacheForTheFirstTime(): Boolean = isReadFromCache

    override fun isAlreadyCachedFirstPage(): Flow<Boolean> =
            dataStore.data.map {
                it[IS_ALREADY_FIRST_CACHED] ?: false
            }

    override fun isAlreadyCachedSecondPage(): Flow<Boolean> =
            dataStore.data.map {
                it[IS_ALREADY_SECOND_CACHED] ?: false
            }

    override fun getCachedFirstPage(): Flow<String> {
        return dataStore.data.map {
            it[FIRST_PAGE_CACHED] ?: ""
        }
    }

    override suspend fun saveToCacheFirstPage(games: List<Game>) {
        val json = Gson().toJson(games)
        dataStore.edit {
            it[FIRST_PAGE_CACHED] = json
            it[IS_ALREADY_FIRST_CACHED] = true
        }

    }

    override fun getCachedSecondPage(): Flow<String> {
        return dataStore.data.map {
            isReadFromCache = true
            it[SECOND_PAGE_CACHED] ?: ""
        }
    }

    override suspend fun saveToCacheSecondPage(games: List<Game>) {
        val json = Gson().toJson(games)
        dataStore.edit {
            it[SECOND_PAGE_CACHED] = json
            it[IS_ALREADY_SECOND_CACHED] = true
            isReadFromCache = true
        }
    }

    companion object {

        val IS_ALREADY_FIRST_CACHED = preferencesKey<Boolean>("is_first_page_already_cached")
        val FIRST_PAGE_CACHED = preferencesKey<String>("first_page_cached")

        val IS_ALREADY_SECOND_CACHED = preferencesKey<Boolean>("is_second_page_already_cached")
        val SECOND_PAGE_CACHED = preferencesKey<String>("second_page_cached")


    }


}