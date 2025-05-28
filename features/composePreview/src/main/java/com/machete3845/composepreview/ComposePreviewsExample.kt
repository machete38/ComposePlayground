package com.machete3845.composepreview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class UserProvider : PreviewParameterProvider<String>{
    override val values = sequenceOf("John", "Alice", "Bob")
}

@Composable
fun UserGreeting(name: String){
    Text("Hello, $name")
}


@Preview(showBackground = true)
@Composable
fun UserGreetingPreview(@PreviewParameter(UserProvider::class) name: String){
    UserGreeting(name)
}