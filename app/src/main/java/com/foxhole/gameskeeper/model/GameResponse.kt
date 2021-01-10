package com.foxhole.gameskeeper.model


data class GameResponse(
    val count: Int,
    val next: String,
    val games: List<Game>
)