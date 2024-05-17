package com.example.charigochi.ui.theme

import android.hardware.lights.Light
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.charigochi.R

/*private val DarkColorScheme = darkColorScheme(
    primary = Pink80,
    secondary = Yellow80,
    tertiary = Pink80,
    onSurface = PinkDark,
    onBackground = Yellow20,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Pink40,
    secondary = Yellow40,
    tertiary = Pink80,
    onSurface = PinkDark,
    onBackground = PinkDark,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
)*/
private val LightColorScheme = lightColorScheme(
    primary = LightPink,
    secondary = LightTeal,
    tertiary = LightBlue,
    background = LightYellow,
    surface = LightLavender,
    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onTertiary = DarkBackground,
    onBackground = DarkBackground,
    onSurface = DarkBackground,
)

val DarkColorScheme = darkColorScheme(
    primary = DarkPink,
    secondary = DarkTeal,
    tertiary = DarkBlue,
    background = DarkBackground,
    surface = DarkLavender,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

// Функция для установки фона с изображением
@Composable
fun BackgroundImage(
    imagePainter: Painter,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Обрезаем изображение, чтобы сохранить его пропорции
        )
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

    val backgroundImagePainter = if (darkTheme) {
        painterResource(id = R.drawable.background_dark) // Замените на идентификатор темного изображения
    } else {
        painterResource(id = R.drawable.background_light) // Замените на идентификатор светлого изображения
    }

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