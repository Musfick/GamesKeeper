package com.foxhole.gameskeeper.ui.singleGame

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.model.GameDetails
import com.foxhole.gameskeeper.model.Screenshot
import com.foxhole.gameskeeper.repositories.singleGame.SingleGameRepo
import com.foxhole.gameskeeper.utils.Event
import com.foxhole.gameskeeper.utils.Resource
import com.foxhole.gameskeeper.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class SingleGameViewModel @ViewModelInject constructor(
        private val singleGameRepo: SingleGameRepo,
        private val responseHandler: ResponseHandler,
        @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _gameId: Int = 0
    private val _game = MutableLiveData<Resource<Game>>()
    val game: LiveData<Resource<Game>> = _game


    private val _favoriteGames = singleGameRepo.getAllFavoriteGames().asLiveData()

    val isFavorite = _favoriteGames.switchMap { games ->
        liveData {
            val game = savedStateHandle.get<Game>("game")
            game?.let {
                _game
                if (games.any { it.id == game.id }) emit(true) else emit(false)
            } ?: emit(false)
        }
    }

    init {
        val game = savedStateHandle.get<Game>("game")
        _gameId = game!!.id

        getGameDetails()
    }

    fun getGameDetails() {
        viewModelScope.launch {

            _game.postValue(Resource.loading(null))
            val gameResponse = singleGameRepo.getGameDetailsById(_gameId)
            val screenshotResponse = singleGameRepo.getGameScreenshotById(_gameId)

            gameResponse.zip(screenshotResponse) { game, _screenshot ->

                game.screenshots = _screenshot.results
                return@zip game

            }.catch { e ->
                _game.postValue(responseHandler.handleException(Exception(e)))
            }.collect {
                _game.postValue(responseHandler.handleSuccess(it))
            }
        }
    }

    fun addGameToFavorite() {
        val game = savedStateHandle.get<Game>("game")
        viewModelScope.launch {
            game?.let {
                singleGameRepo.addGameToFavorite(it)
            }

        }
    }

    fun deleteFavoriteGame() {
        val game = savedStateHandle.get<Game>("game")
        viewModelScope.launch {
            game?.let {
                singleGameRepo.deleteFavoriteGame(it.id)
            }

        }
    }
}