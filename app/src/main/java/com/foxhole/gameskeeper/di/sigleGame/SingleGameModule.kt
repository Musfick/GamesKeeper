package com.foxhole.gameskeeper.di.sigleGame

import android.content.Context
import com.foxhole.gameskeeper.adapter.ScreenshotAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Musfick Jamil on 1/12/2021$.
 */
@Module
@InstallIn(ActivityComponent::class)
object SingleGameModule {

    @Provides
    fun providesScreenshotAdapter(
            @ApplicationContext app: Context
    ) = ScreenshotAdapter(app)
}