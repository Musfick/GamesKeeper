package com.foxhole.gameskeeper.remote.api

import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.model.GameResponse
import com.foxhole.gameskeeper.model.ScreenshotResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("games/{id}")
    suspend fun getGameDetailsById(
            @Path("id") id: Int
    ): Game

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshotById(
            @Path("id") id: Int
    ): ScreenshotResponse
}