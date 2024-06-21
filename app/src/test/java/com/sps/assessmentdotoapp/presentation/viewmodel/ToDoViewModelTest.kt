package com.sps.assessmentdotoapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sps.assessmentdotoapp.domain.usecase.TodoUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class ToDoViewModelTest{

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ToDoViewModel
    private lateinit var mockTodoUseCaseImpl: TodoUseCaseImpl

    @Before
    fun setUp() {
        mockTodoUseCaseImpl = Mockito.mock(TodoUseCaseImpl::class.java)
        viewModel = ToDoViewModel(mockTodoUseCaseImpl)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `add todo - success`() = runTest {
        val newTodoTitle = "Buy Milk"
        Mockito.verifyZeroInteractions(mockTodoUseCaseImpl)
        viewModel.saveData(newTodoTitle)
        advanceUntilIdle()
        Mockito.verify(mockTodoUseCaseImpl).saveTodo(newTodoTitle)
    }


    @Test
    fun testAddTodoItem() = runTest {
        viewModel.saveData("Test Task")
        val todoList = viewModel.state.value.items
        assertEquals(1, todoList.size)
        assertEquals("Test Task", todoList[0].content)
    }

    @Test
    fun testAddEmptyTodoItem() = runTest {
        viewModel.saveData("")
        val todoList = viewModel.state.value.searchItems
        assertEquals(0, todoList.size)
    }

    @Test
    fun `search todos - success`() = runTest {
        viewModel.onSearch("karun")
        val results = viewModel.state.value.searchItems.first()
        assertEquals(listOf("karun is nice dev", "priya is going to home", "kotlin 3"), results)
    }

    @Test
    fun testEmptyQuery() = runTest {
        viewModel.onSearch("")
        val results = viewModel.state.value.searchItems.first()
        assertEquals(emptyList<String>(), results)
    }

    @Test
     fun saveData(){
        runBlocking {
            mockTodoUseCaseImpl.saveTodo("test")
        }

    }
}