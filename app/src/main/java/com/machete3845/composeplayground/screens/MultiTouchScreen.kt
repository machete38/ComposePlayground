package com.machete3845.composeplayground.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import com.machete3845.composeplayground.R

@Composable
fun MultiTouchScreen()
{
    Column {
//        Text("Обработка нескольких касаний")
//        MultiTouchExample()
        Text("Pinch-To-Zoom")
        PinchToZoomExample()
    }
}

@Composable
fun MultiTouchExample(){
    var touchPoints by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (touchPoints > 1) Color.Green else Color.LightGray)
            .pointerInput(Unit){
                awaitEachGesture {
                    awaitFirstDown()

                    do {
                        val event = awaitPointerEvent()

                        touchPoints = event.changes.size
                    }  while (event.changes.any { it.pressed })

                    touchPoints = 0
                }
            }
    )
}

@Composable
fun PinchToZoomExample(){
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var rotation by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_screen),
            contentDescription = "Zoomable Image",
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY,
                    rotationZ = rotation
                )
                .pointerInput(Unit){
                    detectTransformGestures { centroid, pan ,zoom, rotationChange ->

                        scale = (scale * zoom).coerceIn(0.5f, 3f)

                        offsetX += pan.x
                        offsetY += pan.y

                        rotation += rotationChange
                    }
                }
        )
    }
}