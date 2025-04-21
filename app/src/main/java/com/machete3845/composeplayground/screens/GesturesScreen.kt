package com.machete3845.composeplayground.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun GesturesScreen(){
    Column {
        Text("Базовые модификаторы для касаний")
        ClickableExample()
        CombinedClickable()
        Text("Обработка сложных жестов с помощью PointerInputScope")
//        DragExample()
//        TransformableExample()
        Text("Использование готовых модификаторов для жестов")
        ScrollableExample()
        SwipeableExample()
    }
}

@Composable
fun ClickableExample(){
    var clicked by remember { mutableStateOf(false) }

    Column {
        Text("Модификатор clickable")
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(if (clicked) Color.Red else Color.Blue)
                .clickable{ clicked = !clicked },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = if (clicked) "Clicked!" else "Click me!",
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickable(){
    var text by remember { mutableStateOf("Tap or long press")}
    Column {
        Text("Модификатор combinedClickable (для обработки долгих нажатий)")
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.LightGray)
                .combinedClickable(
                    onClick = { text = "Clicked!"},
                    onLongClick = { text = "Long pressed!" },
                    onDoubleClick = { text = "Double clicked!"}
                ),
            contentAlignment = Alignment.Center
        )
        {
            Text(text)
        }
    }

}


@Composable
fun DragExample(){
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Column {
        Text("Обнаружение перетаскивания (drag)")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Box(
                Modifier.offset{ IntOffset(offsetX.roundToInt(), offsetY.roundToInt())}
                    .size(100.dp)
                    .background(Color.Blue)
                    .pointerInput(Unit){
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )
        }
    }
}

@Composable
fun TransformableExample(){
    var scale by remember{ mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f)}
    var offset by remember { mutableStateOf(Offset.Zero) }

    Column {
        Text("Обнаружение жестов масштабирования и поворота")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Box(
                Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .size(200.dp)
                    .background(Color.Blue)
                    .pointerInput(Unit){
                        detectTransformGestures { _, pan, zoom, rotationChange ->
                            scale *= zoom
                            rotation += rotationChange
                            offset += pan
                        }
                    }
            ){

            }
        }
    }
}


// Использование готовых модификаторов для жестов
@Composable
fun ScrollableExample(){
    val scrollState = rememberScrollState()
    Column {
        Text("Модификатор Scrollable")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ){
            repeat(50) {
                index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }

}

@Composable
fun SwipeableExample(){
    val width = 96.dp
    val squareSize = 48.dp

}