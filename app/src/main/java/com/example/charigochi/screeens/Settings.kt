package com.example.charigochi.screeens

import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

@Composable
fun Settings() {
    val context = LocalContext.current

    // Сохранение состояния для звука, языка и темы
    var isSoundOn by rememberSaveable { mutableStateOf(true) }
    var currentLanguage by rememberSaveable { mutableStateOf("ru-RU") }
    var currentTheme by rememberSaveable { mutableStateOf("default") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ЗВУК",
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
                    isSoundOn = true
                    Toast.makeText(context, "Звук включен", Toast.LENGTH_SHORT).show()
                    // Добавьте здесь логику включения звука
                },
                enabled = !isSoundOn,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(text = "Вкл", style = Typography.labelSmall)
            }

            Button(
                onClick = {
                    isSoundOn = false
                    Toast.makeText(context, "Звук выключен", Toast.LENGTH_SHORT).show()
                    // Добавьте здесь логику выключения звука
                },
                enabled = isSoundOn,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(text = "Выкл", style = Typography.labelSmall)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = context.getString(R.string.language_setting),
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
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageButton(
                    painter = painterResource(R.drawable.flafrus),
                    onClick = {
                        currentLanguage = "ru-RU"
                        Toast.makeText(context, "Выбран русский язык", Toast.LENGTH_SHORT).show()
                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("ru-RU")
                        AppCompatDelegate.setApplicationLocales(appLocale)
                    },
                    modifier = Modifier.size(120.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageButton(
                    painter = painterResource(R.drawable.flageng),
                    onClick = {
                        currentLanguage = "en-US"
                        Toast.makeText(context, "Выбран английский язык", Toast.LENGTH_SHORT).show()
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
                    currentTheme = "dark"
                    Toast.makeText(context, "Темная тема", Toast.LENGTH_SHORT).show()
                    // Добавьте здесь логику включения темной темы
                },
                enabled = currentTheme != "dark",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(text = "Темная", style = Typography.labelSmall)
            }

            Button(
                onClick = {
                    currentTheme = "light"
                    Toast.makeText(context, "Светлая тема", Toast.LENGTH_SHORT).show()
                    // Добавьте здесь логику включения светлой темы
                },
                enabled = currentTheme != "light",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(text = "Светлая", style = Typography.labelSmall)
            }

            Button(
                onClick = {
                    currentTheme = "default"
                    Toast.makeText(context, "Тема по умолчанию", Toast.LENGTH_SHORT).show()
                    // Добавьте здесь логику включения темы по умолчанию
                },
                enabled = currentTheme != "default",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 2.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(text = "По умолчанию", style = Typography.labelSmall, textAlign = TextAlign.Center)
            }
        }
    }
}
