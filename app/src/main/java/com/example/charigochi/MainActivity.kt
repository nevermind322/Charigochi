package com.example.charigochi

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.model.Progress
import com.example.charigochi.model.Settings
import com.example.charigochi.model.funds
import com.example.charigochi.model.getLanguageForLocale
import com.example.charigochi.screeens.ChooseCat
import com.example.charigochi.screeens.Donate
import com.example.charigochi.screeens.MenuScreen
import com.example.charigochi.screeens.Settings
import com.example.charigochi.screeens.SomethingWentWrong
import com.example.charigochi.screeens.TamagochiScreen
import com.example.charigochi.screeens.AboutUs
import com.example.charigochi.ui.theme.CharigochiTheme
import com.example.charigochi.ui.theme.DarkPink
import com.example.charigochi.vm.AppUiState
import com.example.charigochi.vm.AppViewModel
import com.example.charigochi.vm.MainActivityViewModel
import com.example.charigochi.vm.SettingsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm by viewModels<MainActivityViewModel>()
        //mediaPlayer = MediaPlayer.create(this.applicationContext, R.raw.lol)
        //mediaPlayer?.isLooping = true
        //mediaPlayer?.start()
        setContent {
            val settingsState by vm.stateFlow.collectAsState()

            when (settingsState) {
                SettingsState.Loading -> Unit
                is SettingsState.Success -> {/*
                    if ((settingsState as SettingsState.Success).isSoundOn)
                        mediaPlayer?.setVolume(1f, 1f)
                    else
                        mediaPlayer?.setVolume(0f, 0f)*/
                    val settings = (settingsState as SettingsState.Success).settings
                    CharigochiTheme(theme = settings.theme) {
                        CharigochiApp(settings)
                        //TamagochiScreen(cat = CATS_INIT[0], vm = hiltViewModel())
                    }

                }
            }
        }
    }


    override fun onPause() {
        super.onPause()
        //mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        //mediaPlayer?.start()
    }

    override fun onDestroy() {
        //mediaPlayer?.release()
        super.onDestroy()
    }
}


@Composable
fun CharigochiApp(settings: Settings, vm: AppViewModel = hiltViewModel()) {

    val state by vm.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.init()
    }

    when (state) {
        AppUiState.Loading -> {
            // Экран загрузки
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = DarkPink, modifier = Modifier.size(100.dp) // Установите желаемый размер
                )
            }
        }

        is AppUiState.Error -> {
            SomethingWentWrong(onRetry = { vm.init() })
        }

        is AppUiState.Success -> {
            val success = state as AppUiState.Success
            val progress by vm.moneyFlow.combine(vm.isRewardClaimedFlow) { money, isRewardClaimedToday ->
                Progress(
                    streak = success.progress.streak,
                    money = money,
                    isRewardClaimedToday = isRewardClaimedToday
                )
            }.collectAsState(initial = success.progress)

            var fact by remember { mutableStateOf(success.catFact) }
            val locale = LocalConfiguration.current.locales[0]

            LaunchedEffect(key1 = locale) {
                fact = vm.getFact(getLanguageForLocale(locale))
            }

            val cats by vm.catsFlow.collectAsState(initial = success.cats)
            MainNavHost(settings = settings, progress = progress, cats = cats, catFact = fact)
        }
    }

}

const val MENU_SCREEN_ROUTE = "menu"
const val CHOOSE_CAT_SCREEN_ROUTE = "chooseCat"
const val SETTINGS_SCREEN_ROUTE = "settings"
const val ABOUT_AUTHORS_SCREEN_ROUTE = "aboutAuthors"
const val TAMAGOCHI_SCREEN_ARGUMENT = "catId"
const val TAMAGOCHI_SCREEN_ROUTE = "tamagochi"
const val DONATE_SCREEN_ROUTE = "donate"


@Composable
fun MainNavHost(settings: Settings, progress: Progress, cats: List<CatEntity>, catFact: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MENU_SCREEN_ROUTE) {

        composable(route = MENU_SCREEN_ROUTE) {
            MenuScreen(
                onSettingsClick = { navController.navigate(SETTINGS_SCREEN_ROUTE) },
                onCatChooseClick = { navController.navigate(CHOOSE_CAT_SCREEN_ROUTE) },
                onAboutUsClick = { navController.navigate(ABOUT_AUTHORS_SCREEN_ROUTE) },
                progress = progress,
                catFact = catFact,
                vm = hiltViewModel()
            )
        }

        composable(route = CHOOSE_CAT_SCREEN_ROUTE) {
            ChooseCat(
                cats = cats,
                money = progress.money,
                onTamagochiClick = { id -> navController.navigate("$TAMAGOCHI_SCREEN_ROUTE/$id") },
                vm = hiltViewModel()
            )
        }

        composable(route = SETTINGS_SCREEN_ROUTE) {
            Settings(settings = settings, vm = hiltViewModel())
        }

        composable(route = ABOUT_AUTHORS_SCREEN_ROUTE) {
            AboutUs()
        }

        composable(
            route = "$TAMAGOCHI_SCREEN_ROUTE/{$TAMAGOCHI_SCREEN_ARGUMENT}",
            arguments = listOf(navArgument(TAMAGOCHI_SCREEN_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getInt(TAMAGOCHI_SCREEN_ARGUMENT) ?: 0
            TamagochiScreen(cat = cats.first { it.id == catId },
                vm = hiltViewModel(),
                onDonateClick = { navController.navigate(DONATE_SCREEN_ROUTE) })
        }

        composable(route = DONATE_SCREEN_ROUTE) {
            Donate(fonds = funds)
        }
    }
}