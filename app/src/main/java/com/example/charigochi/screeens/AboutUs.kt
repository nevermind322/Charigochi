package com.example.charigochi.screeens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charigochi.R
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.ui.theme.Blue80
import com.example.charigochi.ui.theme.Green80
import com.example.charigochi.ui.theme.Grey40
import com.example.charigochi.ui.theme.Grey80
import com.example.charigochi.ui.theme.Pink40
import com.example.charigochi.ui.theme.Pink80
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.ui.theme.Yellow20
import com.example.charigochi.ui.theme.Yellow80
import com.example.charigochi.vm.TamagochiScreenViewModel

@Composable
fun AboutUs() {
    val context = LocalContext.current
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp, horizontal = 10.dp),
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