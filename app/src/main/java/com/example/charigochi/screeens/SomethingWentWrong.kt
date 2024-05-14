package com.example.charigochi.screeens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.charigochi.R

@Composable
fun SomethingWentWrong(onRetry: () -> Unit) {

    val context = LocalContext.current

    var numberOfRetries by remember { mutableIntStateOf(0) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Что-то пошло не так...")
        Image(painter = painterResource(id = R.drawable.sadcat), contentDescription = null)
        Button(onClick = {
            if (numberOfRetries > 3)
                Toast.makeText(context, "Это бесполезно, перезапустите приложение", Toast.LENGTH_SHORT).show()
            else {
                onRetry()
                numberOfRetries++
            }
        }) {
            Text(text = "Попытаться опять")
        }
    }
}

@Composable
@Preview
fun SomethingWentWrongPreview() {
    SomethingWentWrong(onRetry = {})
}