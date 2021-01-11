package com.foxhole.gameskeeper.repositories.main

import androidx.paging.PagingData
import com.foxhole.gameskeeper.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
interface MainRepo {

    fun getAllFavoriteGames(): Flow<List<Game>>

    fun getGamesFromRemote(pageSize: Int): Flow<PagingData<Game>>
}