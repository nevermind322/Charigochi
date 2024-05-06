package com.example.charigochi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.charigochi.data.CatDbDatasource
import com.example.charigochi.data.CatRepo
import com.example.charigochi.screeens.TamagochiScreen
import com.example.charigochi.ui.theme.CharigochiTheme
import com.example.charigochi.vm.AppUiState
import com.example.charigochi.vm.MainActivityViewModel
import com.example.charigochi.vm.TamagochiScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tamagochiScreenViewModel by viewModels<TamagochiScreenViewModel>()
        val mainActivityViewModel by viewModels<MainActivityViewModel>()

        setContent {
            CharigochiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(Unit) {
                        mainActivityViewModel.getCats()
                    }
                    val state by mainActivityViewModel.state.collectAsState()
                    when (state) {
                        AppUiState.Loading -> CircularProgressIndicator()
                        is AppUiState.Error -> Unit
                        is AppUiState.Success -> {
                            val cat = (state as AppUiState.Success).cats[0]
                            TamagochiScreen(cat = cat, vm = tamagochiScreenViewModel)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }
}
