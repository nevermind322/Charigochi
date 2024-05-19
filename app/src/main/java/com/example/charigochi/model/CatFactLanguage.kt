package com.example.charigochi.model

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

enum class CatFactLanguage(val code: String, val default: String) {
    RUSSIAN("rus", "котики милые)"), ENGLISH("eng", "kitties are cute)")
}


@Singleton
class LocaleProvider @Inject constructor(@ApplicationContext private val context: Context) {

    val currentLocale
        get() = context.resources.configuration.locales[0]

    fun getCatFactLanguage() = when (currentLocale.toString()) {
        "ru_RU" -> CatFactLanguage.RUSSIAN
        else -> CatFactLanguage.ENGLISH
    }

}