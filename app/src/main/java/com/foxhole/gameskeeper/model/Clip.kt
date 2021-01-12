package com.foxhole.gameskeeper.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Clip(
        @SerializedName("video")
        val videoid: String
) : Serializable