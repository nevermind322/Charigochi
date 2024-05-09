package com.example.charigochi.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.charigochi.R

val vividSansFontFamily = FontFamily(
    Font(R.font.vividsansregular)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = vividSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = vividSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = vividSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    )

)