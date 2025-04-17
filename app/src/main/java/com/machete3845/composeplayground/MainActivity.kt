package com.machete3845.composeplayground

import android.R.attr.onClick
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Dimension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.machete3845.composeplayground.screens.ThemedScreen
import com.machete3845.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = MainViewModel()
        setContent {
            ComposePlaygroundTheme {
                Column {
                    ThemedScreen()
//                    Text("Анимация размера")
//                    AnimatedSizeExample()
//                    Text("Анимация цвета")
//                    AnimatedColorExample()
//                    Text("Сложная анимация")
//                    TransitionExample()
                }
//                InfiniteScrollList(mainViewModel)
//               ModalBottomSheetExample()
//                Column {
//                    ConstraintLayoutExample()
//                    StaggeredGridExample()
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello $name!",
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            letterSpacing = 0.5.sp,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (image, title, subtitle, button1, button2) = createRefs()

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            "Заголовок",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.top)
                start.linkTo(image.end, margin = 16.dp)
            }
        )

        Text(
            "Подзаголовок с дополнительной информацией",
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, margin = 8.dp)
                start.linkTo(title.start)
                end.linkTo(parent.end)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
            }
        )

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(image.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(button2.start, margin = 8.dp)
                width =androidx.constraintlayout.compose.Dimension.fillToConstraints
            }
        ) {
            Text("Кнопка 1")
        }

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(button1.top)
                start.linkTo(button1.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
            }
        ) {
            Text("Кнопка 2")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePlaygroundTheme {
        Scaffold { innerPadding ->
            Greeting("Andrew", modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier){

    var counter by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Counter $counter")
        Button(onClick = { counter++}){
            Text("Increment")
        }
    }

    DisposableEffect(Unit) {
        Log.d("CounterScreen", "Screen entered")

        onDispose {
            Log.d("CounterScreen", "Screen exited")
        }
    }

}

@Composable
fun ScrollableLists() {
    Column {
        LazyColumn(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            items(100) { index ->
                Text(
                    text = "Вертикальный элемент $index",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            items(50) { index ->
                Text(
                    text = "Горизонтальный элемент $index",
                    modifier = Modifier
                        .padding(16.dp)
                        .width(200.dp)
                )
            }
        }
    }
}

@Composable
fun CombinedExample() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { /* действие */ },
            modifier = Modifier.weight(1f)
        ) {
            Text("Левая")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { /* действие */ },
            modifier = Modifier.weight(1f)
        ) {
            Text("Правая")
        }
    }
}



@Composable
fun InfiniteScrollList(viewModel: MainViewModel) {
    val listState = rememberLazyListState()
    val items = viewModel.items.value

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf false
            lastVisibleItem.index == items.size - 1
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.loadMoreItems()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(items){ item ->
            Text(text = item)
        }
    }
}

