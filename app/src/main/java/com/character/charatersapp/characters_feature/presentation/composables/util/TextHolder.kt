package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextHolder(tag: String = "", text: String,
               fontWeight: FontWeight,
               fontSize: TextUnit,
               fontStyle: FontStyle
) {
    Text(
        text = tag + text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        fontStyle = fontStyle
    )
}