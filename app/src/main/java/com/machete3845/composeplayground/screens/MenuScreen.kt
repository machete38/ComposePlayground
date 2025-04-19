package com.machete3845.composeplayground.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MenuScreen(
    navigateToGesturesScreen: () -> Unit
){
    Button(
        onClick = navigateToGesturesScreen
    ) {
        Text("Жесты")
    }
}