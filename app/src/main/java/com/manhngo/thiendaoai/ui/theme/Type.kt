package com.manhngo.thiendaoai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.manhngo.thiendaoai.R

val VNLovalistaFont = FontFamily(
    Font(R.font.vn_lovelista_regular)
)

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val NotoSerifFont = GoogleFont("Noto Serif")

val NotoSerifFontFamily = FontFamily(
    Font(googleFont = NotoSerifFont, fontProvider = provider),
    Font(googleFont = NotoSerifFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = NotoSerifFont, fontProvider = provider, weight = FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = NotoSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = VNLovalistaFont, // 🔥 dùng font custom
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NotoSerifFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
