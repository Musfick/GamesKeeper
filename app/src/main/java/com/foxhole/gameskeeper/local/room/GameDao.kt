package com.foxhole.gameskeeper.local.room

import androidx.room.Dao
import androidx.room.Query
import com.foxhole.gameskeeper.base.BaseDao
import com.foxhole.gameskeeper.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@Dao
abstract class GameDao : BaseDao<Game> {

    //Get all games
    @Query("SELECT * FROM games_table")
    abstract fun getGames(): Flow<List<Game>>
}