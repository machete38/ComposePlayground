package com.machete3845.composeplayground.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
        DragExample()
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