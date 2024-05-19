package com.example.charigochi.model

import android.content.Context
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