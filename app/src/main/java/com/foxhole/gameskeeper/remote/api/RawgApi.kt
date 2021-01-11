package com.foxhole.gameskeeper.remote.api

import com.foxhole.gameskeeper.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
interface RawgApi {

    @GET("games")
    suspend fun getGamesPageByPage(
        @Query("dates") date: String,
        @Query("ordering") order: String,
        @Query("page") page: Int
    ): GameResponse
}