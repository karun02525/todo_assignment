package com.sps.assessmentdotoapp.presentation.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sps.assessmentdotoapp.presentation.ui.screens.AddItemScreen
import com.sps.assessmentdotoapp.presentation.ui.screens.HomeScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("addItem") { AddItemScreen(navController) }
    }
}