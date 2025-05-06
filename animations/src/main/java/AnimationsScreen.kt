import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimationsScreen() {
    AnimationsScreenView()
}

@Composable
private fun AnimationsScreenView() {
    Column {
        Text("В Jetpack Compose доступны два основных типа анимаций: высокоуровневые и низкоуровневые")
        Text("Высокоуровневые анимации включают:")
        AnimatedVisibilityExample()
        AnimatedContentExample()
        CrossfadeExample()
        AnimateContentSizeExample()
        Text("Низкоуровневые анимации предоставляют более тонкий контроль и включают:")
        TargetBasedAnimationExample()
        DecayAnimationExample()
        AnimatableExample()
        Text("Бесконечные анимации")
        InfiniteTransitionExample()
    }
}

@Composable
private fun InfiniteTransitionExample() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(Modifier.background(color))
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
    )
    {
        Text("animateContentSize - анимация изменения размера содержимого")
        Box(
            Modifier
                .background(color = Color.LightGray)
                .clickable { expanded = !expanded}
                .animateContentSize()
                .padding(16.dp)

        )
        {
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