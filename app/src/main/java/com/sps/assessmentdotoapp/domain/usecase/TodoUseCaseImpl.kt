package com.sps.assessmentdotoapp.domain.usecase

import com.sps.assessmentdotoapp.data.repository.TodoRepository
import com.sps.assessmentdotoapp.domain.model.Note
import javax.inject.Inject

class TodoUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
) : TodoUseCase {

    override suspend fun saveTodo(content: String) {
        repository.saveTodo(content)
    }

    override suspend fun getAllTodos(): List<Note> {
       return repository.getAllTodos()
    }

    override suspend fun getTodos(word: String): List<Note> {
       return  repository.getTodos(word)
    }
}