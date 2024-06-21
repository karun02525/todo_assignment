package com.sps.assessmentdotoapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import com.sps.assessmentdotoapp.domain.usecase.TodoUseCaseImpl
import com.sps.assessmentdotoapp.presentation.ui.screens.SearchScreen
import com.sps.assessmentdotoapp.presentation.viewmodel.ToDoViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private lateinit var viewModel: ToDoViewModel
    private lateinit var mockTodoUseCaseImpl: TodoUseCaseImpl

    @Before
    fun setUp() {
        mockTodoUseCaseImpl = Mockito.mock(TodoUseCaseImpl::class.java)
        viewModel = ToDoViewModel(mockTodoUseCaseImpl)
    }

    @Test
    fun testSearchResultsDisplayed() {
        composeTestRule.setContent {
            SearchScreen(viewModel)
        }

        composeTestRule.onNodeWithText("").performTextInput("Delhi")

        composeTestRule.onNodeWithText("this is delhi 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("pune  2").assertIsDisplayed()
        composeTestRule.onNodeWithText("mumbai 3").assertIsDisplayed()
    }

    @Test
    fun testEmptySearchResults() {
        composeTestRule.setContent {
            SearchScreen(viewModel)
        }

        composeTestRule.onNodeWithText("").performTextInput("")
        composeTestRule.onNodeWithText("ramm 1").assertDoesNotExist()
        composeTestRule.onNodeWithText("sitta 2").assertDoesNotExist()
    }
}
