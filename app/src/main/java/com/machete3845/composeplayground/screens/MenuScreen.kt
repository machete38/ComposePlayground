package com.machete3845.composeplayground.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.machete3845.composeplayground.navigation.NavRoutes

@Composable
fun MenuScreen(
    navigateToGesturesScreen: () -> Unit,
    navigateToDragAndDropScreen: () -> Unit,
    navigateToMultiTouchScreen: () -> Unit,
    navigateToAnimationScreen: () -> Unit,
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

        Button(
            onClick = navigateToMultiTouchScreen
        ) {
            Text(NavRoutes.MULTI_TOUCH)
        }
        Button(
            onClick = navigateToAnimationScreen
        ) {
            Text(NavRoutes.ANIMATIONS)
        }
    }
}