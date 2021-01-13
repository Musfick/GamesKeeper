package com.foxhole.gameskeeper.datasource

import androidx.paging.PagingSource
import com.foxhole.gameskeeper.local.datastore.CachePreferences
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.model.GameResponse
import com.foxhole.gameskeeper.remote.api.RawgApi
import com.foxhole.gameskeeper.utils.Constants.NO_INTERNET
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class ExplorePagingSource(
        private val rawgApi: RawgApi,
        private val cachePreferences: CachePreferences
) : PagingSource<Int, Game>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {

        val nextParamNumber = params.key ?: 1

        return try {
            val response =
                    rawgApi.getGamesPageByPage("2020-01-01,2020-12-31", "-added", nextParamNumber)
            val games = response.results

            LoadResult.Page(games, null, nextParamNumber.inc())
        } catch (e: IOException) {
            LoadResult.Error(Exception(NO_INTERNET))
        } catch (e: HttpException) {
            LoadResult.Error(Exception(NO_INTERNET))
        }
    }

}