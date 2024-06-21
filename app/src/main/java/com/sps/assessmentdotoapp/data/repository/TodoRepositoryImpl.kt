package com.sps.assessmentdotoapp.data.repository
import com.sps.assessmentdotoapp.data.mappers.TodoMapper
import com.sps.assessmentdotoapp.domain.model.Note
import com.sps.database.domain.DatabaseService
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val databaseService: DatabaseService,
    private val todoMapper: TodoMapper
) : TodoRepository {

    override suspend fun saveTodo(content: String) {
        databaseService.saveTodo(content)
    }

    override suspend fun getAllTodos(): List<Note> {
      return databaseService.getAllTodos().map(todoMapper::toDomain)
    }

    override suspend fun getTodos(word: String): List<Note> {
        return databaseService.getTodos(word).map(todoMapper::toDomain)
    }
}