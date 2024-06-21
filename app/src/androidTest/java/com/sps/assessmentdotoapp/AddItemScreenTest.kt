package com.sps.assessmentdotoapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.sps.assessmentdotoapp.presentation.ui.screens.AddItemScreen
import org.junit.Rule
import org.junit.Test

class AddItemScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAddTodoItem() {
        composeTestRule.setContent {
            AddItemScreen()
        }

        composeTestRule.onNodeWithText("").performTextInput("Test Task")
        composeTestRule.onNodeWithText("Save").performClick()
        composeTestRule.onNodeWithText("Test Task").assertIsDisplayed()
    }

    @Test
    fun testEmptyTodoItem() {
        composeTestRule.setContent {
            AddItemScreen()
        }

        composeTestRule.onNodeWithText("Save").performClick()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
    }
}
