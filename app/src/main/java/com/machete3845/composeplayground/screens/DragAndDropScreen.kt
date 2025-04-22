package com.machete3845.composeplayground.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DragAndDropScreen(){
    Column {
//        Text("Простое перетаскивание элемента")
//        DraggableItem()
        DragAndDropExample()
    }
}

@Composable
fun DraggableItem()
{
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        var offsetX by remember { mutableFloatStateOf(0f) }
        var offsetY by remember { mutableFloatStateOf(0f) }

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit){
                    detectDragGestures {
                        change, dragAmount ->
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                },
            contentAlignment = Alignment.Center
        )
        {
            Text(text = "Перетащи меня", color = Color.White)
        }
    }
}



data class DraggableItem(
    val id: Int,
    val text: String,
    var container: Int
)

@Composable
fun DragAndDropExample() {
    val items = remember {
        mutableStateListOf(
            DraggableItem(1, "Элемент 1", 1),
            DraggableItem(2, "Элемент 2", 1),
            DraggableItem(3, "Элемент 3", 2)
        )
    }

    var draggedItem by remember { mutableStateOf<DraggableItem?>(null) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var draggedItemPosition by remember { mutableStateOf(Offset.Zero) }

    var container1Position by remember { mutableStateOf(Offset.Zero) }
    var container1Size by remember { mutableStateOf(IntSize.Zero) }

    var container2Position by remember { mutableStateOf(Offset.Zero) }
    var container2Size by remember { mutableStateOf(IntSize.Zero) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Первый контейнер
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(2.dp, Color.Gray)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .onGloballyPositioned { coordinates ->
                    container1Position = coordinates.positionInWindow()
                    container1Size = coordinates.size
                }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Контейнер 1", modifier = Modifier.padding(bottom = 8.dp))
                items.filter { it.container == 1 }.forEach { item ->
                    if (draggedItem?.id != item.id) {
                        DraggableItemContent(
                            item = item,
                            onDragStart = { offset ->
                                draggedItem = item
                                dragOffset = offset
                            },
                            onDrag = { offset ->
                                draggedItemPosition = offset
                            },
                            onDragEnd = {
                                val itemCenter = draggedItemPosition + dragOffset

                                if (itemCenter.x >= container2Position.x &&
                                    itemCenter.x <= container2Position.x + container2Size.width &&
                                    itemCenter.y >= container2Position.y &&
                                    itemCenter.y <= container2Position.y + container2Size.height) {
                                    val index = items.indexOfFirst { it.id == item.id }
                                    if (index != -1) {
                                        items[index] = items[index].copy(container = 2)
                                    }
                                }

                                draggedItem = null
                            }
                        )
                    }
                }
            }
        }

        // Второй контейнер
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(2.dp, Color.Gray)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .onGloballyPositioned { coordinates ->
                    container2Position = coordinates.positionInWindow()
                    container2Size = coordinates.size
                }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Контейнер 2", modifier = Modifier.padding(bottom = 8.dp))
                items.filter { it.container == 2 }.forEach { item ->
                    if (draggedItem?.id != item.id) {
                        DraggableItemContent(
                            item = item,
                            onDragStart = { offset ->
                                draggedItem = item
                                dragOffset = offset
                            },
                            onDrag = { offset ->
                                draggedItemPosition = offset
                            },
                            onDragEnd = {
                                val itemCenter = draggedItemPosition + dragOffset

                                // Проверяем, находится ли элемент над первым контейнером
                                if (itemCenter.x >= container1Position.x &&
                                    itemCenter.x <= container1Position.x + container1Size.width &&
                                    itemCenter.y >= container1Position.y &&
                                    itemCenter.y <= container1Position.y + container1Size.height) {
                                    // Перемещаем элемент в первый контейнер
                                    val index = items.indexOfFirst { it.id == item.id }
                                    if (index != -1) {
                                        items[index] = items[index].copy(container = 1)
                                    }
                                }

                                draggedItem = null
                            }
                        )
                    }
                }
            }
        }
    }

    // Отображаем перетаскиваемый элемент поверх всего
    draggedItem?.let { item ->
        Box(
            modifier = Modifier
                .offset { IntOffset(
                    (draggedItemPosition.x - dragOffset.x).toInt(),
                    (draggedItemPosition.y - dragOffset.y).toInt()
                ) }
                .width(200.dp)
                .height(50.dp)
                .background(Color.Blue)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(item.text, color = Color.White)
        }
    }
}

@Composable
fun DraggableItemContent(
    item: DraggableItem,
    onDragStart: (Offset) -> Unit,
    onDrag: (Offset) -> Unit,
    onDragEnd: () -> Unit
) {
    var itemPosition by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Blue)
            .onGloballyPositioned { coordinates ->
                itemPosition = coordinates.positionInWindow()
            }
            .pointerInput(item.id) {
                detectDragGestures(
                    onDragStart = { offset ->
                        onDragStart(offset)
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        onDrag(itemPosition)
                    },
                    onDragEnd = {
                        onDragEnd()
                    },
                    onDragCancel = {
                        onDragEnd()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(item.text, color = Color.White)
    }
}