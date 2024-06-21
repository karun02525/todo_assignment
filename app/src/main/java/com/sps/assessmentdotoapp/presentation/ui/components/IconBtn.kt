package com.sps.assessmentdotoapp.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconBtn(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = "Icons"
        )
    }
}