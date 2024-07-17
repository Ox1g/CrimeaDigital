package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Greeting(){
    var text by remember {
        mutableStateOf("Привет!!!")
    }
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { text = "Кнопка нажата!" }) {
            Text("Нажми меня!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    Greeting()
}