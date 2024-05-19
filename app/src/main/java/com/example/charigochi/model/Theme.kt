package com.example.charigochi.model

const val DARK_THEME_KEY = "dark"
const val LIGHT_THEME_KEY = "light"
const val DEFAULT_THEME_KEY = "default"

enum class Theme(val key: String) {
   Dark(DARK_THEME_KEY), Light(LIGHT_THEME_KEY), Default(DEFAULT_THEME_KEY)
}

fun getTheme(key: String) = when (key) {
   LIGHT_THEME_KEY -> Theme.Light
   DARK_THEME_KEY -> Theme.Dark
   else -> Theme.Default
}