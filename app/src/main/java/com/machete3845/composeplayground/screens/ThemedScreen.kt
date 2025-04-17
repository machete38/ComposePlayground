package com.machete3845.composeplayground.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ThemedScreen(){
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val shapes = MaterialTheme.shapes

    Surface(
        color = colors.surface,
        shape = shapes.medium
    ) {
        Text(
            text = "Hello, Compose",
            style = typography.bodyLarge,
            color = Color.Red
        )
    }
}