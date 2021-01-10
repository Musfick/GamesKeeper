package com.foxhole.gameskeeper.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foxhole.gameskeeper.model.Game

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@Database(
    entities = [Game::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}