package com.example.charigochi.screeens

import android.app.LocaleManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.charigochi.R
import com.example.charigochi.ui.theme.Typography


@Composable
fun ImageButton(painter: Painter, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier
        )
    }
}
//TODO звук вкл/выкл
@Composable
fun Settings() {
    val context = LocalContext.current
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
        Text(
            text = "ЗВУК",
            style = Typography.titleLarge.copy(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        var volume by remember { mutableStateOf(0f) }

        val animatedVolume by animateFloatAsState(
            targetValue = volume,
            animationSpec = tween(durationMillis = 300)
        )
        Slider(
            value = animatedVolume,
            onValueChange = { newVolume ->
                volume = newVolume
                // Здесь вы можете выполнить логику изменения громкости в вашем приложении
            },
            valueRange = 0f..100f,
            steps = 5,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            colors = SliderDefaults.colors()
        )
        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text =  context.getString(R.string.language_setting),
            style = Typography.titleLarge.copy(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f).padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageButton(
                    painter = painterResource(R.drawable.flafrus),
                    onClick = {
                        Toast.makeText(context, "Кнопка 1", Toast.LENGTH_SHORT).show()
                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("ru-RU")
                        AppCompatDelegate.setApplicationLocales(appLocale)
                    },
                    modifier = Modifier.size(120.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f).padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageButton(
                    painter = painterResource(R.drawable.flageng),
                    onClick = {
                        Toast.makeText(context, "Кнопка 2", Toast.LENGTH_SHORT).show()
                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en-US")
                        AppCompatDelegate.setApplicationLocales(appLocale)
                    },
                    modifier = Modifier.size(120.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "ТЕМА",
            style = Typography.titleLarge.copy(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Кнопка 1", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp)) // Указываем радиус скругления углов
            ) {
                Text(text = "Темная", style = Typography.labelSmall)
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Кнопка 2", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp)) // Указываем радиус скругления углов
            ) {
                Text(text = "Светлая", style = Typography.labelSmall)
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Кнопка 2", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 2.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp)) // Указываем радиус скругления углов
            ) {
                Text(text = "По умолчанию", style = Typography.labelSmall, textAlign = TextAlign.Center,)

            }
        }

    }

}