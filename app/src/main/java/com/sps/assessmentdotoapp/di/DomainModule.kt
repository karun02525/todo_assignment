package com.sps.assessmentdotoapp.di

import com.sps.assessmentdotoapp.data.mappers.TodoMapper
import com.sps.assessmentdotoapp.data.repository.TodoRepository
import com.sps.assessmentdotoapp.data.repository.TodoRepositoryImpl
import com.sps.assessmentdotoapp.domain.usecase.TodoUseCase
import com.sps.assessmentdotoapp.domain.usecase.TodoUseCaseImpl
import com.sps.database.domain.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideTodoUseCaseImpl(
        todoRepository: TodoRepository
    ): TodoUseCase {
        return TodoUseCaseImpl(todoRepository)
    }
}