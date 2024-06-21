package com.sps.assessmentdotoapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sps.assessmentdotoapp.presentation.theme.AssessmentDOTOAppTheme
import com.sps.assessmentdotoapp.presentation.theme.GreenDark
import com.sps.assessmentdotoapp.presentation.ui.navigation.NavigationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssessmentDOTOAppTheme {
                SetStatusBarColor(color = GreenDark)
                NavigationScreen()
            }
        }
    }
}


@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = color,
        darkIcons = color.luminance() > 0.5f
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssessmentDOTOAppTheme {
       NavigationScreen()
    }
}