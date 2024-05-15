package com.lindenlabs.repospotlight.di

import android.app.Application
import android.content.Context
import com.lindenlabs.repospotlight.data.api.AppDataSource
import com.lindenlabs.repospotlight.data.api.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext


    @Provides
    fun provideAppDataSource(): AppDataSource = AppRepository()
}