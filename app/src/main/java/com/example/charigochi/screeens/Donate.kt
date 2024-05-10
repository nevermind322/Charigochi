package com.example.charigochi.screeens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.ui.theme.Pink80
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.ui.theme.Yellow20

@Composable
fun Donate(fonds: List<String>) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Фоновый цвет
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Yellow20
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 50.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Выберите организацию для пожертвования:",
                    style = Typography.titleLarge.copy(color = Pink80),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (fond in fonds)
                        FondButton(fond = fond)


                }
            }
        }
    }
}

@Composable
fun FondButton(fond: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            //val url = "https://www.example.com" // ваша ссылка здесь
            //val intent = Intent(Intent.ACTION_VIEW)
            //intent.data = Uri.parse(url)
            //startActivity(intent)
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(300.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
    ) {
        Text(text = "Фонд $fond", style = Typography.titleLarge)
    }
}