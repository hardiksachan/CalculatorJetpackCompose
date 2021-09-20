package com.hardiksachan.calculatorjetpackcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hardiksachan.calculatorjetpackcompose.R

val VarelaRound = FontFamily(
    Font(R.font.varela_round_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = VarelaRound,
    h1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 93.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 58.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 33.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    ),
)