package com.example.charigochi

import android.health.connect.datatypes.AppInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.screeens.Donate
import com.example.charigochi.screeens.MainScreen
import com.example.charigochi.screeens.TamagochiScreen
import com.example.charigochi.ui.theme.BackgroundImage
import com.example.charigochi.ui.theme.CharigochiTheme
import com.example.charigochi.vm.AppUiState
import com.example.charigochi.vm.AppViewModel
import com.example.charigochi.vm.TamagochiScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel by viewModels<AppViewModel>()
        setContent {
            CharigochiTheme {
                BackgroundImage(
                    imagePainter = painterResource(id = R.drawable.background) // Замените на ваш ресурс изображения
                ) {
                    //CharigochiApp(appViewModel)
                    //MainScreen()
                    Donate()

                }
            }
        }
    }
}

@Composable
fun CharigochiApp(vm: AppViewModel) {

    LaunchedEffect(Unit) {
        vm.updateCats()
    }

    val state by vm.state.collectAsState()

    when (state) {
        AppUiState.Loading -> {
            // Экран загрузки
        }

        is AppUiState.Error -> {
            // Экран "что-то пошло не так", я его напишу, но нужна картинка грустного котика
        }

        is AppUiState.Success -> {
            MainNavHost()
        }
    }

}

const val MENU_SCREEN_ROUTE = "menu"
const val CHOOSE_CAT_SCREEN_ROUTE = "chooseCat"
const val SETTINGS_SCREEN_ROUTE = "settings"
const val ABOUT_AUTHORS_SCREEN_ROUTE = "aboutAuthors"
const val TAMAGOCHI_SCREEN_ARGUMENT = "catId"
const val TAMAGOCHI_SCREEN_ROUTE = "tamagochi/{$TAMAGOCHI_SCREEN_ARGUMENT}"
const val DONATE_SCREEN_ROUTE = "donate"


@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MENU_SCREEN_ROUTE) {

        composable(route = MENU_SCREEN_ROUTE) {

        }

        composable(route = CHOOSE_CAT_SCREEN_ROUTE) {

        }

        composable(route = SETTINGS_SCREEN_ROUTE) {

        }

        composable(route = ABOUT_AUTHORS_SCREEN_ROUTE) {

        }

        composable(
            route = TAMAGOCHI_SCREEN_ROUTE,
            arguments = listOf(navArgument(TAMAGOCHI_SCREEN_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getInt(TAMAGOCHI_SCREEN_ARGUMENT) ?: 0

        }

        composable(route = DONATE_SCREEN_ROUTE) {

        }
    }

}