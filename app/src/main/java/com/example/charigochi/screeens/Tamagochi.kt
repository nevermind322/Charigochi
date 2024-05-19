package com.example.charigochi.screeens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charigochi.R
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.vm.TamagochiScreenViewModel

@Composable
fun ImageProgressBar(
    progress: Float,
    maxImages: Int,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val imageWidth = maxWidth / maxImages
        val displayedImages = (progress * maxImages).toInt()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageWidth),
            horizontalArrangement = Arrangement.Start
        ) {
            repeat(displayedImages) {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.size(imageWidth),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun CatHungerProgress(catHunger: Float) {
    val imagePainter = painterResource(id = R.drawable.fish) // Измените на ваш ресурс изображения

    ImageProgressBar(
        progress = catHunger / 100,
        maxImages = 6, // Количество изображений для полного прогресса
        imagePainter = imagePainter,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CatHappinessProgress(catHappiness: Float) {
    val imagePainter = painterResource(id = R.drawable.cat) // Измените на ваш ресурс изображения

    ImageProgressBar(
        progress = catHappiness / 100,
        maxImages = 6, // Количество изображений для полного прогресса
        imagePainter = imagePainter,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TamagochiScreen(cat: CatEntity, vm: TamagochiScreenViewModel, onDonateClick: () -> Unit) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onDonateClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(70.dp)
        ) {
            Text(text = "Оформить пожертование", fontSize = 18.sp, maxLines = 1)
        }
        Box {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = null,
                modifier = Modifier.clickable { vm.care(cat) }
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
            ) {
                Button(
                    onClick = {
                        vm.feed(cat)
                        Toast.makeText(context, "Hello from Bottom Button 2", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fishn1),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        // Прогресс для счастья

        CatHappinessProgress(catHappiness = cat.happiness.toFloat())

        // Прогресс для голода

        CatHungerProgress(catHunger = cat.bellyful.toFloat())
    }
}