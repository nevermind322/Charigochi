package com.example.charigochi.screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.R
import com.example.charigochi.ui.theme.Pink80
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.ui.theme.Yellow20

@Composable
fun MenuScreen(onSettingsClick: () -> Unit, onCatChooseClick: () -> Unit, onAboutUsClick: () -> Unit) {
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
                    text = "До разблокировки нового котика:",
                    style = Typography.titleLarge.copy(color = Pink80),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Any дней",
                    style = Typography.bodyLarge.copy(color = Pink80),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
                val imageModifier = Modifier
                    .width(330.dp)
                    .height(330.dp)
                val image: Painter = painterResource(id = R.drawable.logotext2)

                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = imageModifier.align(Alignment.CenterHorizontally)
                        .padding(vertical = 12.dp)

                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            onCatChooseClick()
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(300.dp)
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
                    ) {
                        Text(text = "ИГРАТЬ", style = Typography.titleLarge)
                    }

                    Button(
                        onClick = {
                           onSettingsClick()
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(200.dp)
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов

                    ) {
                        Text(text = "Настройки", style = Typography.titleLarge)
                    }

                    Button(
                        onClick = {
                            onAboutUsClick()
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(200.dp)
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
                    ) {
                        Text(text = "Об авторах", style = Typography.titleLarge)
                    }
                }

            }
        }
    }
}