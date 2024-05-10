package com.example.charigochi.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import com.example.charigochi.R

private val DarkColorScheme = darkColorScheme(
    primary = Pink80,
    secondary = Yellow80,
    tertiary = Blue80,
)

private val LightColorScheme = lightColorScheme(
    primary = Pink40,
    secondary = Yellow40,
    tertiary = Blue40,
    onSurface = Color(0xFF1C1B1F),
    /* Other default colors to override

    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


// Функция для установки фона с изображением
@Composable
fun BackgroundImage(imagePainter: Painter, content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Отображение изображения на фоне
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            // ContentScale.FillBounds позволяет заполнить всю доступную область изображением, сохраняя его пропорции
            contentScale = ContentScale.Crop
        )
        // Отображение остального содержимого поверх изображения
        content()
    }
}

@Composable
fun CharigochiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val backgroundImagePainter = painterResource(id = R.drawable.background_light) // Замените на ваш ресурс изображения

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            BackgroundImage(imagePainter = backgroundImagePainter) {
                content()
            }
        }
    )
}
