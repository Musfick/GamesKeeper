package com.foxhole.gameskeeper.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "games_table")
data class Game(
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("id")
    val gameId: Int,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)