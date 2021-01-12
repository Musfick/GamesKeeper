package com.foxhole.gameskeeper.repositories.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.foxhole.gameskeeper.datasource.ExplorePagingSource
import com.foxhole.gameskeeper.local.room.GameDao
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.remote.api.RawgApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class MainRepoImpl(
        private val gameDao: GameDao,
        private val rawgApi: RawgApi
) : MainRepo {
    override fun getAllFavoriteGames(): Flow<List<Game>> = gameDao.getGames()

    override fun getGamesFromRemote(pageSize: Int): Flow<PagingData<Game>> = Pager(
        PagingConfig(pageSize)
    ) {
        ExplorePagingSource(rawgApi)
    }.flow
}