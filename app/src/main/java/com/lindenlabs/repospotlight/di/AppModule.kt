package com.lindenlabs.repospotlight.di

import android.app.Application
import android.content.Context
import com.lindenlabs.repospotlight.data.AppDataSource
import com.lindenlabs.repospotlight.data.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext


    @Provides
    fun provideAppDataSource(): AppDataSource = AppRepository()
}