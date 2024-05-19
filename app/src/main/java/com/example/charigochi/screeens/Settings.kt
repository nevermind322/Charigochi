package com.example.charigochi.screeens

import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.charigochi.data.Theme
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.vm.SettingsViewModel

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
fun Settings(vm: SettingsViewModel) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground

    // Сохранение состояния для звука, языка и темы
    var isSoundOn by rememberSaveable { mutableStateOf(true) }
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
            style = Typography.titleLarge.copy(color = onBackgroundColor),
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
                    vm.changeSound(true)
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
                    vm.changeSound(false)
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
            style = Typography.titleLarge.copy(color = onBackgroundColor),
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
            style = Typography.titleLarge.copy(color = onBackgroundColor),
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
                    vm.changeTheme(Theme.Dark)
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
                    vm.changeTheme(Theme.Light)
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
                    vm.changeTheme(Theme.Default)
                },
                enabled = currentTheme != "default",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 2.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "По умолчанию",
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
