package com.example.charigochi.screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.data.db.price
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.vm.ChooseCatViewModel


@Composable
fun ChooseCat(
    cats: List<CatEntity>,
    money: Int,
    onTamagochiClick: (Int) -> Unit,
    vm: ChooseCatViewModel
) {

    ChooseCat(cats = cats,
        money = money,
        onTamagochiClick = onTamagochiClick,
        onBuyButtonClick = { id -> vm.buyCat(id) })
}

@Composable
fun ChooseCat(
    cats: List<CatEntity>,
    money: Int,
    onTamagochiClick: (Int) -> Unit,
    onBuyButtonClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Фоновый цвет
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 50.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "$money $",
                    style = Typography.titleLarge.copy(color = onBackgroundColor),
                    modifier = Modifier
                        .width(80.dp)
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    items(cats.size) { index ->
                        val cat = cats[index]
                        CatCard(
                            cat = cat,
                            onTamagochiClick = onTamagochiClick,
                            BuyButtonState(enabled = cat.price <= money, onClick = onBuyButtonClick)
                        )
                    }
                }
            }
        }
    }
}

data class BuyButtonState(
    val enabled: Boolean,
    val onClick: (Int) -> Unit
)

@Composable
fun CatCard(cat: CatEntity, onTamagochiClick: (Int) -> Unit, buyButtonState: BuyButtonState) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(230.dp) // Высота карточки котика
            .clip(RoundedCornerShape(8.dp)) // Указываем радиус скругления углов
            .clickable { if (cat.unlocked) onTamagochiClick(cat.id) }
    ) {
        // Цифра количества смертей в верхнем правом углу
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Red)
        ) {
            Text(
                text = cat.deaths.toString(),
                color = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Добавляем пространство между элементами
        ) {
            Text(
                text = cat.name,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                maxLines = 1, // Ограничиваем количество строк
                overflow = TextOverflow.Ellipsis, // Используем многоточие, если текст не помещается
                modifier = Modifier.fillMaxWidth() // Ширина текста равна ширине карточки
            )
            Spacer(modifier = Modifier.height(8.dp)) // Вертикальный отступ между текстом и изображением

            // Изображение котика
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = null,
                modifier = if (!cat.unlocked) Modifier
                    .height(100.dp) // Устанавливаем фиксированную высоту изображения
                    .fillMaxWidth()
                else Modifier
                    .height(150.dp) // Устанавливаем фиксированную высоту изображения
                    .fillMaxWidth(),
                colorFilter = if (!cat.unlocked) ColorFilter.tint(Color.Gray) else null
            )
            Spacer(modifier = Modifier.height(8.dp)) // Вертикальный отступ между изображением и кнопкой цены

            // Кнопка цены, если котик не разблокирован
            if (!cat.unlocked) {
                Button(
                    onClick = {
                        // Действие при нажатии на кнопку цены
                        buyButtonState.onClick(cat.id)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp) // Добавляем отступы для кнопки
                        .height(40.dp), // Устанавливаем фиксированную высоту кнопки
                    enabled = buyButtonState.enabled
                ) {
                    Text(
                        text = "${cat.price} $",
                        style = Typography.titleSmall.copy()
                    ) // Ограничиваем количество строк и используем многоточие
                }
            }
        }
    }
}