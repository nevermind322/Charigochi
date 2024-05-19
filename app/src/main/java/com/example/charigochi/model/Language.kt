package com.example.charigochi.model

import android.content.Context
import com.example.charigochi.utils.getMoneyBonusForStreak
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

enum class Language(val code: String, val default: String) {
    RUSSIAN("rus", "котики милые)"), ENGLISH("eng", "kitties are cute)")
}


@Singleton
class LocaleProvider @Inject constructor(@ApplicationContext private val context: Context) {

    val currentLocale
        get() = context.resources.configuration.locales[0]

    fun getCatFactLanguage() = getLanguageForLocale(currentLocale)
}


fun getLanguageForLocale(locale : Locale) = when (locale.toString()) {
    "ru_RU" -> Language.RUSSIAN
    else -> Language.ENGLISH
}

fun getRightWord(days: Int, language: Language): String {
    return when (language) {
        Language.ENGLISH -> {
            when (days) {
                1 -> "day"
                else -> "days"
            }
        }

        Language.RUSSIAN -> {
            val preLastNum = days % 100 / 10
            if (preLastNum == 1)
                "дней"
            else {
                val lastNum = days % 10
                when (lastNum) {
                    1 -> "день"
                    in 2..4 -> "дня"
                    else -> "дней"
                }
            }
        }
    }
}

fun getStreakRewardText(language: Language, streak: Int): String {
    val daysWord = getRightWord(days = streak, language = language)
    val moneyBonus = getMoneyBonusForStreak(streak)
    return when(language) {
        Language.RUSSIAN -> "Вы заходили $streak $daysWord подряд и получили $moneyBonus $!"
        Language.ENGLISH -> TODO()
    }
}