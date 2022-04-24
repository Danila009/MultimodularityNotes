package com.example.core_ui.enum

import androidx.compose.ui.graphics.Color
import com.example.core_ui.primaryBackground
import com.example.core_ui.secondaryBackground

enum class ColorNote(val color: Color) {
    SECONDARY(color = secondaryBackground),
    PRIMARY(color = primaryBackground),
    WHERE(color = Color(0xFFFFFFFF)),
    BLACK(color = Color(0xFF000000)),
    RED(color = Color(0xFFB80F0F)),
    GREEN(color = Color(0xFF1DC211)),
    YELLOW(color = Color(0xFFCEB90A)),
    BLUE(color = Color(0xFF08E4B0)),
    ORANGE(color = Color(0xFFFF8400)),
    PURPLE(color = Color(0xFFB300FF)),
    LIGHT_RED(color = Color(0xFFFF9E9E)),
    LIGHT_GREEN(color = Color(0xFF91F48F)),
    LIGHT_YELLOW(color = Color(0xFFFFF599)),
    LIGHT_PURPLE(color = Color(0xFFB69CFF))
}