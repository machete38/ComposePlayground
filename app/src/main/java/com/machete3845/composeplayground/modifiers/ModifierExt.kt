package com.machete3845.composeplayground.modifiers

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.time.Duration


fun Modifier.customBorder(
    width: Dp = 1.dp,
    color: Color = Color.Black,
    shape: Shape = RectangleShape
): Modifier{
    return this
        .clip(shape)
        .border(width = width, color = color, shape = shape)
}


fun Modifier.pulsatingEffect(
    pulseFraction: Float = 0.2f,
    duration: Int = 1000
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()
    val scale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1f + pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        )
    )
    this.scale(scale.value)
}