package com.machete3845.composeplayground

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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

@Composable
fun AnimatedSizeExample(){
    var expanded by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Blue)
    )
    {
        Button(
            onClick = { expanded = !expanded}
        ) {
            Text(text = if (expanded) "Уменьшить" else "Увеличить")
        }
    }

}

@Composable
fun AnimatedColorExample(){
    var isRed by remember { mutableStateOf(true) }

    val color by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.Gray
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
            .clickable { isRed = !isRed }
    )
}

@Composable
fun PulsatingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .scale(scale)
                .size(100.dp)
                .background(Color.Red)
        )
    }


}

@Composable
fun TransitionExample(){
    var state by remember { mutableStateOf(BallState.Start) }

    val transition = updateTransition(targetState = state, label = "BallTransition")

    val offsetX by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000)},
        label = "offsetX"
    ){
        ballState ->
        when(ballState){
            BallState.Start ->  0f
            BallState.End -> 300f
        }
    }

    val offsetY by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000)},
        label = "offsetY"
    ){
        ballState ->
        when(ballState){
            BallState.Start -> 0f
            BallState.End -> 200f
        }
    }

    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000)},
        label = "scale"
    ){
        ballState ->
        when (ballState){
            BallState.Start -> 1f
            BallState.End -> 2f
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(offsetX.dp, offsetY.dp)
                .scale(scale)
                .clip(CircleShape)
                .background(Color.Red)
                .clickable{
                    state = when (state){
                        BallState.Start -> BallState.End
                        BallState.End -> BallState.Start
                    }
                }
        )
    }
}

enum class BallState { Start, End }