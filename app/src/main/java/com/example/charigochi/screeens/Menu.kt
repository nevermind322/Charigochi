package com.example.charigochi.screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charigochi.R
import com.example.charigochi.ui.theme.Typography
import com.example.charigochi.vm.AppUiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.charigochi.ui.theme.DarkColorScheme

@Composable
fun MenuScreen(
    success: AppUiState.Success,
    onSettingsClick: () -> Unit,
    onCatChooseClick: () -> Unit,
    onAboutUsClick: () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground

    val imageResourceId = if (MaterialTheme.colorScheme == DarkColorScheme) {
        R.drawable.logo_dark2 // Замените на идентификатор темного изображения
    } else {
        R.drawable.logo_light // Замените на идентификатор светлого изображения
    }
    val image: Painter = painterResource(id = imageResourceId)

    // Фоновый цвет
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Баланс: ${success.money}",
                style = Typography.titleLarge.copy(color = onBackgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )

            val imageModifier = Modifier
                .width(330.dp)
                .height(330.dp)

            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onCatChooseClick,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(300.dp)
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(primaryColor)
                ) {
                    Text(
                        text = "ИГРАТЬ",
                        style = Typography.titleLarge.copy(color = onPrimaryColor)
                    )
                }

                Button(
                    onClick = onSettingsClick,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(200.dp)
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(primaryColor)
                ) {
                    Text(
                        text = "Настройки",
                        style = Typography.titleLarge.copy(color = onPrimaryColor)
                    )
                }

                Button(
                    onClick = onAboutUsClick,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(200.dp)
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(primaryColor)
                ) {
                    Text(
                        text = "Об авторах",
                        style = Typography.titleLarge.copy(color = onPrimaryColor)
                    )
                }
            }
            Text(
                text = success.catFact,
                style = Typography.bodyMedium.copy(color = onBackgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}
@Composable
fun RewardDialog(onConfirm: () -> Unit, ) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onConfirm,
        title = {
            Text(text =  "yes", style = Typography.bodyMedium)
        },
        text = {
            Text(text =  "yes", style = Typography.bodyMedium)
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text =  "thank u", style = Typography.bodyMedium)
            }
        }
    )
}