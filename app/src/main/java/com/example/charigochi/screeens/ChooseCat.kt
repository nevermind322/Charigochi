package com.example.charigochi.screeens

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.charigochi.R
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.ui.theme.Green80
import com.example.charigochi.ui.theme.Grey40
import com.example.charigochi.ui.theme.Pink80
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.ui.theme.Yellow20
import com.example.charigochi.vm.TamagochiScreenViewModel

@Composable
fun ChooseCat(cats: List<CatEntity>) {
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
                    text = "Выберите котика:",
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
                    for (cat in cats)
                        CatCard(cat = cat)


                }
            }
        }
    }
}

@Composable
fun CatCard(cat: CatEntity) {
    val context = LocalContext.current
    Button(
        onClick = {
            // Действие при нажатии на карточку котика
            Toast.makeText(context, "Выбран котик: ${cat.id}", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(300.dp)
            .height(200.dp) // Высота карточки котика
            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
    ) {
        // Содержимое карточки котика
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Вертикальный отступ между изображением и текстом
            // Имя котика
            Text(
                text = cat.name,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() // Ширина текста равна ширине карточки
            )
            Image(painter = painterResource(id = cat.imageRes), contentDescription =null)
        }
    }
}
