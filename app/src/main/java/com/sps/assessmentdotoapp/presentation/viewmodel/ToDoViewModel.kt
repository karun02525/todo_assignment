package com.sps.assessmentdotoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sps.assessmentdotoapp.domain.model.Note
import com.sps.assessmentdotoapp.domain.usecase.TodoUseCaseImpl
import com.sps.assessmentdotoapp.presentation.ui.state.ItemViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val todoUseCaseImpl: TodoUseCaseImpl
) : ViewModel() {

    private val _state = MutableStateFlow(ItemViewState())
    val state = _state.asStateFlow()


    fun getItems() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(items = todoUseCaseImpl.getAllTodos()) }
            delay(1000)
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun saveData(content: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000)
            todoUseCaseImpl.saveTodo(content = content)
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun onSearch(word: String="") {
        if(word.length>1) {
            viewModelScope.launch {
                delay(100)
                _state.update {
                    it.copy(
                        searchItems = todoUseCaseImpl.getTodos(word)
                            .filter { s -> s.content.contains(word) }
                    )
                }
            }
        }else{
            _state.update {
                it.copy(searchItems = emptyList())
            }
        }
    }
}