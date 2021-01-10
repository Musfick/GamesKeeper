package com.foxhole.gameskeeper.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.foxhole.gameskeeper.getOrAwaitValue
import com.foxhole.gameskeeper.model.Game
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Musfick Jamil on 1/10/2021$.
 */
@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class GameDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: GameDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.gameDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertGame() = runBlockingTest {
        val game = Game("background_image", 120, "csgo", id = 1)
        dao.insert(game)

        val allGames = dao.getGames().asLiveData().getOrAwaitValue()

        assertThat(allGames).contains(game)
    }

    @Test
    fun updateGame() = runBlockingTest {
        val newGame = Game("background_image", 120, "csgo", id = 1)

        dao.insert(newGame)

        val updateGame = Game("background_image", 100, "Counter Strike", id = 1)

        dao.update(updateGame)

        val games = dao.getGames().asLiveData().getOrAwaitValue()

        assertThat(games).contains(updateGame)
    }

    @Test
    fun deleteGame() = runBlockingTest {
        val game = Game("background_image", 120, "csgo", id = 1)
        dao.insert(game)
        dao.delete(game)

        val games = dao.getGames().asLiveData().getOrAwaitValue()

        assertThat(games).doesNotContain(game)
    }

    @Test
    fun getAllGames() = runBlockingTest {
        val game = Game("background_image", 120, "csgo", id = 1)
        val game1 = Game("background_image", 121, "csgo1", id = 2)
        val game2 = Game("background_image", 122, "csgo2", id = 3)

        dao.insert(game)
        dao.insert(game1)
        dao.insert(game2)

        val games = dao.getGames().asLiveData().getOrAwaitValue()

        assertThat(games.size).isEqualTo(3)

    }
}