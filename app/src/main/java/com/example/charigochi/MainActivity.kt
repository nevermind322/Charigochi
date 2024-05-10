package com.example.charigochi

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.screeens.AboutUs
import com.example.charigochi.screeens.ChooseCat
import com.example.charigochi.screeens.MenuScreen
import com.example.charigochi.screeens.Settings
import com.example.charigochi.screeens.SomethingWentWrong
import com.example.charigochi.ui.theme.CharigochiTheme
import com.example.charigochi.vm.AppUiState
import com.example.charigochi.vm.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharigochiTheme {
                CharigochiApp()
                //MainScreen()
                //Donate()
                //Settings()

            }
        }
    }

}


val Context.dataStore by preferencesDataStore(name = "settings")


@Composable
fun CharigochiApp(vm: AppViewModel = hiltViewModel()) {

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.init()
    }

    when (state) {
        AppUiState.Loading -> {
            // Экран загрузки
            CircularProgressIndicator(color = Color.Cyan)
        }

        is AppUiState.Error -> {
            // Экран "что-то пошло не так", я его напишу, но нужна картинка грустного котика
            SomethingWentWrong(onRetry = { vm.init() })
        }

        is AppUiState.Success -> {
            MainNavHost((state as AppUiState.Success).cats)
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
fun MainNavHost(cats: List<CatEntity>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MENU_SCREEN_ROUTE) {

        composable(route = MENU_SCREEN_ROUTE) {
            MenuScreen(onSettingsClick = { navController.navigate(SETTINGS_SCREEN_ROUTE) },
                onCatChooseClick = { navController.navigate(CHOOSE_CAT_SCREEN_ROUTE) },
                onAboutUsClick = { navController.navigate(ABOUT_AUTHORS_SCREEN_ROUTE) })
        }

        composable(route = CHOOSE_CAT_SCREEN_ROUTE) {
            ChooseCat(cats = cats)

        }

        composable(route = SETTINGS_SCREEN_ROUTE) {
            Settings()
        }

        composable(route = ABOUT_AUTHORS_SCREEN_ROUTE) {
            AboutUs()
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