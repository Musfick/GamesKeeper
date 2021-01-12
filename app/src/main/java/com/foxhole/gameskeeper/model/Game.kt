package com.foxhole.gameskeeper.model


import androidx.room.Entity
import androidx.room.Ignore
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
) : Serializable {
    @Ignore
    var screenshots: MutableList<Screenshot>? = null

    @Ignore
    @SerializedName("clip")
    var video: Clip? = null

    @Ignore
    val rating: Float? = null

    @Ignore
    val released: String? = null

    @Ignore
    val description: String? = null
}