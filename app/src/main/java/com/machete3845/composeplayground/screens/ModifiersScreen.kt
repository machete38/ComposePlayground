package com.machete3845.composeplayground.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.machete3845.composeplayground.modifiers.customBorder
import com.machete3845.composeplayground.modifiers.pulsatingEffect

@Composable
fun ModifiersScreen()
{
    Screen()
}

@Composable
private fun Screen(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Button(onClick = {}, modifier = Modifier
            .customBorder()
            .pulsatingEffect()
            ) {
            Text("Кнопка с модификатором")
        }
    }
    }