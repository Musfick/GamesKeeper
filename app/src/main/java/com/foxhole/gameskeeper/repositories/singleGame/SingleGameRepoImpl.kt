package com.foxhole.gameskeeper.repositories.singleGame

import com.foxhole.gameskeeper.local.room.GameDao
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.model.ScreenshotResponse
import com.foxhole.gameskeeper.remote.api.RawgApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class SingleGameRepoImpl(
        val rawgApi: RawgApi,
        val gameDao: GameDao
) : SingleGameRepo {
    override suspend fun getGameDetailsById(id: Int): Flow<Game> = flow {
        emit(rawgApi.getGameDetailsById(id))
    }

    override suspend fun getGameScreenshotById(id: Int): Flow<ScreenshotResponse> = flow {
        emit(rawgApi.getGameScreenshotById(id))
    }

    override suspend fun addGameToFavorite(game: Game) {
        gameDao.insert(game)
    }

    override fun getAllFavoriteGames(): Flow<List<Game>> = gameDao.getGames()
    override suspend fun deleteFavoriteGame(id: Int) = gameDao.deleteGameById(id)
}