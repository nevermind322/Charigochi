package com.example.charigochi.screeens

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.charigochi.R
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.data.db.imageRes
import com.example.charigochi.vm.TamagochiScreenViewModel

@Composable
fun TamagochiScreen(cat: CatEntity, vm: TamagochiScreenViewModel) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                Toast.makeText(context, "добавьте карту", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(70.dp)

        ) {
            Text(text = "Оформить пожертование", fontSize = 18.sp, maxLines = 1)
        }

        Image(
            painter = painterResource(id = cat.imageRes),
            contentDescription = null,
            modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    "Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            })


        Button(
            onClick = {
                vm.care(cat)
                Toast.makeText(context, "Hello from Bottom Button 2", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.height(80.dp)
        ) {
            Text(text = "Bottom Button 2")
        }

        LinearProgressIndicator(
            progress = { cat.happiness.toFloat() / 100 },
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red
        )

        LinearProgressIndicator(
            progress = { cat.bellyful.toFloat() / 100 },
            modifier = Modifier.fillMaxWidth(),
        )

    }
}