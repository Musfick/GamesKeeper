package com.foxhole.gameskeeper.local.datastore

import com.foxhole.gameskeeper.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * Created by Musfick Jamil on 1/12/2021$.
 */
interface CachePreferences {

    fun isAlreadyCachedFirstPage(): Flow<Boolean>
    fun isAlreadyCachedSecondPage(): Flow<Boolean>

    fun isReadFromCacheForTheFirstTime(): Boolean

    fun getCachedFirstPage(): Flow<String>
    suspend fun saveToCacheFirstPage(games: List<Game>)

    fun getCachedSecondPage(): Flow<String>
    suspend fun saveToCacheSecondPage(games: List<Game>)


}