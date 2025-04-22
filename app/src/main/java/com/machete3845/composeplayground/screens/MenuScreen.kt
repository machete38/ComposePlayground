package com.machete3845.composeplayground.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MenuScreen(
    navigateToGesturesScreen: () -> Unit,
    navigateToDragAndDropScreen: () -> Unit
){
    Column {
        Button(
            onClick = navigateToGesturesScreen
        ) {
            Text("Жесты")
        }
        Button(
            onClick = navigateToDragAndDropScreen
        ) {
            Text("Перетаскивание")
        }
    }
}