package com.sps.assessmentdotoapp.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sps.assessmentdotoapp.presentation.theme.GreenDark
import com.sps.assessmentdotoapp.presentation.ui.components.AppToolbar
import com.sps.assessmentdotoapp.presentation.ui.components.LoadingDialog
import com.sps.assessmentdotoapp.presentation.viewmodel.ToDoViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ToDoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var clickSearch by remember {
        mutableStateOf(false)
    }

    val scaffoldState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var fabVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getItems()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                fabVisible = index == 0
            }
    }


    LoadingDialog(state.isLoading)
    Scaffold(
        snackbarHost = { SnackbarHost(scaffoldState) },
        topBar = {
            AppToolbar() {
                clickSearch = it
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addItem")
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .offset { if (fabVisible) IntOffset(0, 0) else IntOffset(0, 200) }
                    .zIndex(1f),
                containerColor = GreenDark,
                shape = CircleShape,
                contentColor = Color.Black,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (clickSearch) {
                SearchScreen(viewModel)
            } else {
                if (state.items.isEmpty()) {
                    EmptyMessage()
                } else {
                    TodoListScreen(state.items,listState)
                }
            }
        }
    }
}

@Composable
private fun EmptyMessage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Press the + button to add a TODO item",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.Black.copy(alpha = 0.5f),
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(20.dp)
        )
    }
}
