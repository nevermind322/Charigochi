package com.example.charigochi.screeens

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.ui.theme.Typography

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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 50.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Выберите котика:",
                    style = Typography.titleLarge.copy(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cats.chunked(2).forEach { catPair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (cat in catPair) {
                                CatCard(cat = cat)
                            }
                        }
                    }
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
            .padding(8.dp)
            .width(150.dp)
            .height(200.dp) // Высота карточки котика
            .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
    ) {
        // Содержимое карточки котика
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Имя котика
            Text(
                text = cat.name,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() // Ширина текста равна ширине карточки
            )
            Spacer(modifier = Modifier.height(8.dp)) // Вертикальный отступ между изображением и текстом
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = null
            )
        }
    }
}