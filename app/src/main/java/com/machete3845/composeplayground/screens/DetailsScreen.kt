package com.machete3845.composeplayground.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsScreen(
    itemId: Int?,
    name: String?,
    onNavigateUp: () -> Unit,
    onNavigateToProfile: () -> Unit){

    Text("ID элемента: $itemId\nИмя: ${name ?: "Не указано"}")
}