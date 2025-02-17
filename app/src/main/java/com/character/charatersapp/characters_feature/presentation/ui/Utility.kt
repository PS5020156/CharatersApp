package com.character.charatersapp.characters_feature.presentation.ui

import androidx.compose.ui.graphics.Color
import com.character.charatersapp.characters_feature.presentation.ui.theme.Gryffindor
import com.character.charatersapp.characters_feature.presentation.ui.theme.Hufflepuff
import com.character.charatersapp.characters_feature.presentation.ui.theme.RavenClaw
import com.character.charatersapp.characters_feature.presentation.ui.theme.Slytherin

fun getHouseColor(houseName: String): Color {
    return when (houseName.lowercase()) {
        "gryffindor" -> Gryffindor
        "slytherin" -> Slytherin
        "ravenclaw" -> RavenClaw
        "hufflepuff" -> Hufflepuff
        else -> Color.Gray
    }
}