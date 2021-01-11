package com.foxhole.gameskeeper.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.foxhole.gameskeeper.repositories.main.MainRepo

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class MainViewModel @ViewModelInject constructor(mainRepo: MainRepo) : ViewModel() {

    val favoriteGames = mainRepo.getAllFavoriteGames().asLiveData()

    val explore = mainRepo.getGamesFromRemote(20).cachedIn(viewModelScope)
}