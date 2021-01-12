package com.foxhole.gameskeeper.repositories.singleGame

import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.model.ScreenshotResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
interface SingleGameRepo {

    suspend fun getGameDetailsById(id: Int): Flow<Game>

    suspend fun getGameScreenshotById(id: Int): Flow<ScreenshotResponse>

    suspend fun addGameToFavorite(game: Game)

    fun getAllFavoriteGames(): Flow<List<Game>>

    suspend fun deleteFavoriteGame(id: Int)
}