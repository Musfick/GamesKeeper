package com.foxhole.gameskeeper.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "games_table")
data class Game(
    @SerializedName("background_image")
    val backgroundImage: String,
    val id: Int,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val pk: Int? = null
) : Serializable