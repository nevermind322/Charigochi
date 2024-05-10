package com.example.charigochi.screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.charigochi.R

@Composable
fun SomethingWentWrong(onRetry: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Что-то пошло не так...")
        Image(painter = painterResource(id = R.drawable.sadcat), contentDescription = null)
        Button(onClick = onRetry) {
            Text(text = "Попытаться опять")
        }
    }
}

@Composable
@Preview
fun SomethingWentWrongPreview() {
    SomethingWentWrong(onRetry = {})
}