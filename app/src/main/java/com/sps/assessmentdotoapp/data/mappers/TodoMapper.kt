package com.sps.assessmentdotoapp.data.mappers

import com.sps.assessmentdotoapp.domain.model.Note
import com.sps.database.common.Mapper
import com.sps.database.data.entity.TodoEntity
import javax.inject.Inject

class TodoMapper @Inject constructor() :
    Mapper<TodoEntity, Note> {
    override fun toDomain(from: TodoEntity): Note {
        return Note(content = from.notes)
    }
}
