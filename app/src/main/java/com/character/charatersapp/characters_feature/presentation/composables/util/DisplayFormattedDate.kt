package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.character.charatersapp.characters_feature.presentation.ui.DATE_PATTERN_MM
import com.character.charatersapp.characters_feature.presentation.ui.DATE_PATTERN_MMM
import com.character.charatersapp.characters_feature.presentation.ui.INVALID_DATE
import com.character.charatersapp.characters_feature.presentation.ui.TextSizeMedium
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DisplayFormattedDate(dateOfBirth: String) {
    val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_MM)
    val formattedDate = try {
        val date = LocalDate.parse(dateOfBirth, formatter)
        val outputFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN_MMM)
        date.format(outputFormatter)
    } catch (e: Exception) {
        INVALID_DATE
    }

    Text(
        text = formattedDate,
        fontWeight = FontWeight.Bold,
        fontSize = TextSizeMedium,
        fontStyle = FontStyle.Normal
    )
}