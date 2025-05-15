import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AnimationsScreen() {
    AnimationsScreenView()
}

@Composable
private fun AnimationsScreenView() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
//        Text("В Jetpack Compose доступны два основных типа анимаций: высокоуровневые и низкоуровневые")
//        Text("Высокоуровневые анимации включают:")
//        AnimatedVisibilityExample()
//        AnimatedContentExample()
//        CrossfadeExample()
//        AnimateContentSizeExample()
//        Text("Низкоуровневые анимации предоставляют более тонкий контроль и включают:")
//        TargetBasedAnimationExample()
//        DecayAnimationExample()
//        AnimatableExample()
//        Text("Бесконечные анимации")
//        InfiniteTransitionExample()
//        Text("Спецификации анимаций (Animation Specs)\n" +
//                " \n" +
//                "Для настройки поведения анимаций используются различные спецификации:")
        TweenExample()
        SpringExample()
        KeyframesExample()
        RepeatableExample()
    }
}

@Composable
private fun RepeatableExample() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = Color.Green, targetValue = Color.Blue, animationSpec = infiniteRepeatable(
            animation = tween(1000), repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier
            .background(color)
            .size(100.dp)
    )
}

@Composable
private fun KeyframesExample() {
    var target by remember { mutableStateOf(0f) }
    val xOffset by animateFloatAsState(
        targetValue = target, animationSpec = keyframes {
            durationMillis = 3000
            0f at 0
            100f at 1000
            200f at 2000
            300f at 3000
        })
    LaunchedEffect(Unit) { target = 300f }
    Column {
        Text("Keyframes - Анимация позиции с ключевыми кадрами")
        Box(
            modifier = Modifier
                .offset(x = xOffset.dp)
                .size(50.dp)
                .background(Color.Green)
        )
    }

}


@Composable
private fun SpringExample() {
    var isScaled by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isScaled) 2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )

    Column {
        Text("spirng - эффект «пружины» с анимацией масштаба")
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .background(Color.Red)
                .clickable { isScaled = !isScaled })
    }

}

@Composable
private fun TweenExample() {
    var visible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f, animationSpec = tween(durationMillis = 5000)
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    Column {
        Text(
            "tween – линейная анимация с заданной продолжительностью\n" + "Плавное изменение прозрачности (fade-in) за 5 секунд"
        )

        Box(
            Modifier
                .size(100.dp)
                .background(Color.Blue.copy(alpha = alpha))
        )
    }
}


@Composable
private fun InfiniteTransitionExample() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red, targetValue = Color.Green, animationSpec = infiniteRepeatable(
            animation = tween(1000), repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .background(color)
                .size(100.dp)
        )
    }
}

@Composable
private fun AnimatableExample() {

}

@Composable
private fun DecayAnimationExample() {
}

@Composable
private fun TargetBasedAnimationExample() {
    Column {
        Text("TargetBasedAnimation: Управление временем и значениями")

    }
}

@Composable
private fun AnimateContentSizeExample() {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.background(Color.Magenta)
    ) {
        Text("animateContentSize - анимация изменения размера содержимого")
        Box(Modifier
            .background(color = Color.LightGray)
            .clickable { expanded = !expanded }
            .animateContentSize()
            .padding(16.dp)

        ) {
            Text(if (expanded) "Расширенный текст с большим количеством строк" else "Короткий текст")
        }
    }
}

@Composable
private fun CrossfadeExample() {
    var currentScreen by remember { mutableStateOf("А") }
    Column(
        modifier = Modifier.background(Color.Yellow)
    ) {
        Text("Crossfade - плавное затухание между состояниями.")
        Crossfade(targetState = currentScreen) { screen ->
            when (screen) {
                "А" -> Text("Экран А")
                "Б" -> Text("Экран Б")
            }
        }
        Button(
            onClick = {
                currentScreen = if (currentScreen == "А") "Б" else "А"
            }) {
            Text("Переключить экран")
        }
    }
}

@Composable
private fun AnimatedVisibilityExample() {
    var isVisible by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.background(Color.Red)
    ) {
        Text("AnimatedVisibility - анимация появления и исчезновения элементов")
        AnimatedVisibility(
            visible = isVisible, enter = fadeIn() + expandIn(), exit = fadeOut() + shrinkOut()
        ) {
            Text(
                text = "Я появляюсь и исчезаю с анимацией"
            )
        }
        Button(onClick = { isVisible = !isVisible }) {
            Text("Показать текст")
        }
    }
}

@Composable
private fun AnimatedContentExample() {
    var count by remember { mutableStateOf(0) }
    Column(modifier = Modifier.background(Color.Green)) {
        Text("AnimatedContent - анимация перехода между контентом.")
        AnimatedContent(
            targetState = count
        ) { targetCount ->
            Text("Count: $targetCount")
        }
        Button(onClick = { count++ }) {
            Text("Увеличить")
        }
    }
}

@Composable
private fun AnimateFloatAsStateExample() {
    val alpha by animateFloatAsState(
        targetValue = 1f, animationSpec = tween(durationMillis = 1000)
    )
    Box(modifier = Modifier.alpha(alpha)) {
        Text("Hi!")
    }

}