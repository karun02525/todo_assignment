package com.sps.assessmentdotoapp.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sps.assessmentdotoapp.R
import com.sps.assessmentdotoapp.presentation.theme.GreenDark
import com.sps.assessmentdotoapp.presentation.theme.GreenLight
import com.sps.assessmentdotoapp.presentation.ui.components.LoadingDialog
import com.sps.assessmentdotoapp.presentation.viewmodel.ToDoViewModel
import com.sps.assessmentdotoapp.utils.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    navController: NavController? = null,
    viewModel: ToDoViewModel = hiltViewModel()
) {
    var noteEditText by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isLoading by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(state.isLoading) {
        isLoading = state.isLoading
    }

    LoadingDialog(isLoading)


    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(GreenLight)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    if (noteEditText.isNotBlank()) {
                         context.toast("Do you want to clear message? if yes then please remove entered messages!!")
                    } else {
                        navController?.popBackStack()
                    }
                },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }

            Text(
                text = "Add Item",
                fontWeight = FontWeight.W500,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        //Edit Field
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = noteEditText,
                onValueChange = { noteEditText = it },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Please enter your item") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            Button(
                onClick = {
                    if(noteEditText.isNotEmpty()) {
                        viewModel.saveData(noteEditText)
                        scope.launch {
                            delay(1100)
                            context.toast("Item added successfully!!")
                        }
                        noteEditText = ""
                    }else{
                        context.toast("Please enter your notes")
                    }
                },
                colors = ButtonDefaults.buttonColors(GreenDark),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = Color.White,

                    )
            }
        }
    }
}