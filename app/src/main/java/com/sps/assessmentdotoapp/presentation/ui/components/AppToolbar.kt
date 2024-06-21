package com.sps.assessmentdotoapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sps.assessmentdotoapp.presentation.theme.AssessmentDOTOAppTheme
import com.sps.assessmentdotoapp.presentation.theme.GreenLight

@Composable
fun AppToolbar(onClick:(clickSearch:Boolean)->Unit) {
    var clickSearch by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreenLight),
        contentAlignment = Alignment.Center,
    ) {
        if (!clickSearch) {
            IconBtn(
                Icons.Filled.Close,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                onClick(clickSearch)
                clickSearch = true
            }
        } else {
            IconBtn(
                Icons.Filled.Search,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                onClick(clickSearch)
                clickSearch = false
            }
        }
        Text(
            text = if (!clickSearch) "Search notes" else "Auto Hide or Extend FAB",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPrev() {
    AssessmentDOTOAppTheme {
        AppToolbar({})
    }
}