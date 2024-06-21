package com.sps.assessmentdotoapp.di

import com.sps.assessmentdotoapp.data.mappers.TodoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataMapperModule {

    @Provides
    @Singleton
    fun provideTodoMapper(): TodoMapper = TodoMapper()
}