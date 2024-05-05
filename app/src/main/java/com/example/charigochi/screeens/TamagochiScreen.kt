package com.example.charigochi.screeens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charigochi.R

@Composable
fun TamagochiScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
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
            painter = painterResource(id = R.drawable.koshka),
            contentDescription = null,
            modifier = Modifier.size(500.dp),
            contentScale = ContentScale.FillBounds
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Hello from Bottom Button 1", Toast.LENGTH_SHORT).show()
                    Log.d("hi", "bottom button 1 clicked")
                },
                modifier = Modifier.height(80.dp)
            ) {
                Text(text = "Bottom Button 1")
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Hello from Bottom Button 2", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.height(80.dp)
            ) {
                Text(text = "Bottom Button 2")
            }
        }
    }
}