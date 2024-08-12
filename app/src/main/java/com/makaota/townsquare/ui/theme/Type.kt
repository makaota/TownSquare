package com.makaota.townsquare.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val sourceSans3 = FontFamily(
    Font(R.font.sourcesans3_black, FontWeight.Black),
    Font(R.font.sourcesans3_bold, FontWeight.Bold),
    Font(R.font.sourcesans3_extrabold, FontWeight.ExtraBold),
    Font(R.font.sourcesans3_extralight, FontWeight.ExtraLight),
    Font(R.font.sourcesans3_light, FontWeight.Light),
    Font(R.font.sourcesans3_medium, FontWeight.Medium),
    Font(R.font.sourcesans3_regular, FontWeight.Normal),
    Font(R.font.sourcesans3_semibold, FontWeight.SemiBold),

)