package com.machete3845.composeplayground

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedContentBox(
    initialState: Boolean = false,
    content: @Composable (state: Boolean, onStateChange: (Boolean) -> Unit) -> Unit
) {
    var currentState by remember { mutableStateOf(initialState) }

    Box(
        modifier = Modifier
            .background(
                color = Color.Gray
            )
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .size(if (currentState) 200.dp else 100.dp),
    ) {
        content(currentState) { newState ->
            currentState = newState
        }
    }
}