package com.example.charigochi.screeens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.ui.theme.Grey80
import com.example.charigochi.ui.theme.Typography

@Composable
fun AboutUs() {
    val context = LocalContext.current
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp, horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
        Text(
            text = "Цель нашего приложения",
            style = Typography.titleLarge.copy(color = Grey80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Мы столкнулись с необходимостью привлечения внимания пользователей к поддержке фондов и приютов, которые заботятся о бездомных животных.",
            style = Typography.bodyLarge.copy(color = Grey80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Задачей было познакомить Вас с конкретными фондами и замотивировать оформить разовый донат в игровой форме.",
            style = Typography.bodyLarge.copy(color = Grey80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Разработчики:",
            style = Typography.titleLarge.copy(color = Grey80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Филонов Алексей и Павлова Татьяна",
            style = Typography.bodyLarge.copy(color = Grey80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center

        )
    }

}