package com.example.charigochi.screeens

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.data.db.price
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.vm.ChooseCatViewModel


@Composable
fun ChooseCat(
    catsInit: List<CatEntity>,
    moneyInit: Int,
    onTamagochiClick: (Int) -> Unit,
    vm: ChooseCatViewModel
) {
    val cats by vm.allCatsFlow.collectAsState(initial = catsInit)
    val money by vm.moneyFlow.collectAsState(initial = moneyInit)

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
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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

@Stable
data class BuyButtonState(val enabled: Boolean, val onClick: (Int) -> Unit)

@Composable
fun CatCard(cat: CatEntity, onTamagochiClick: (Int) -> Unit, buyButtonState: BuyButtonState) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .padding(8.dp)
        .width(150.dp)
        .height(200.dp) // Высота карточки котика
        .clip(RoundedCornerShape(16.dp)) // Указываем радиус скругления углов
        .clickable { if (cat.unlocked) onTamagochiClick(cat.id) }) {
        // Цифра количества смертей в верхнем правом углу
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Red)
        ) {
            Text(
                text = cat.deaths.toString(), color = Color.White, modifier = Modifier.padding(4.dp)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cat.name,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() // Ширина текста равна ширине карточки
            )
            Spacer(modifier = Modifier.height(8.dp)) // Вертикальный отступ между изображением и текстом

            // Изображение котика
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
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
                        Toast.makeText(context, "Цена: 100", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = buyButtonState.enabled
                ) {
                    Text(text = "Цена: ${cat.price}") // Здесь должна быть логика получения цены
                }
            }
        }
    }
}
