package com.machete3845.composecanvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CanvasScreen(){
    Screen()
}

@Composable
private fun Screen(){
    Column{
        Text("Для начала работы с Canvas используется компонент Canvas")
        BasicCanvasCircle()
        Text("Canvas позволяет рисовать различные примитивы:")
        ShapesCanvas()
    }
}

@Composable
fun ShapesCanvas() {
   Canvas(Modifier.size(300.dp))
   {
       // Линия
       drawLine(
           color = Color.Blue,
           start = Offset(50f, 50f),
           end = Offset(250f, 50f),
           strokeWidth = 5f
       )

       // Прямоугольник
       drawRect(
           color = Color.Green,
           topLeft = Offset(50f, 100f),
           style = Stroke(width = 3f) // Контур вместо заливки
       )

       // Круг
       drawCircle(
           color = Color.Red,
           center = Offset(150f, 250f),
           radius = 40f
       )

       // Овал
       drawOval(
           color = Color.Magenta,
           topLeft = Offset(50f, 300f),
           size = Size(200f, 100f)
       )

       // Дуга
       drawArc(
           color = Color.Cyan,
           startAngle = 0f,
           sweepAngle = 270f,
           useCenter = false,
           topLeft = Offset(50f, 450f),
           size = Size(200f, 100f),
           style = Stroke(width = 5f)
       )

   }
}

@Composable
fun BasicCanvasCircle() {
    Canvas(Modifier.size(100.dp)){ // Size содержит ширину и высоту Canvas
        // Рисуем круг в центре Canvas
        drawCircle(
            color = Color.Red,
            center = Offset(x = size.width / 2, y = size.height / 2),
            radius = 40f
        )
    }
}