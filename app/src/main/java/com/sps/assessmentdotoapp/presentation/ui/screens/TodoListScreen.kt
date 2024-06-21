package com.sps.assessmentdotoapp.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sps.assessmentdotoapp.domain.model.Note

@Composable
fun TodoListScreen(items: List<Note>, listState: LazyListState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
        contentPadding = PaddingValues(bottom = 80.dp),
    ) {
        items(items) { item ->
            Items(item)
        }
    }
}

@Composable
private fun Items(
    item: Note,
) {
    Text(
        text = item.content,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.W500,
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .fillMaxWidth()
    )
}