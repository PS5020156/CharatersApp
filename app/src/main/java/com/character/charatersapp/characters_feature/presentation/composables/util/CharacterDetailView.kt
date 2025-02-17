package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.character.charatersapp.characters_feature.domain.model.Character
import com.character.charatersapp.characters_feature.presentation.ui.ALIVE
import com.character.charatersapp.characters_feature.presentation.ui.DEAD
import com.character.charatersapp.characters_feature.presentation.ui.LargeMargin
import com.character.charatersapp.characters_feature.presentation.ui.MediumMargin
import com.character.charatersapp.characters_feature.presentation.ui.SmallMargin
import com.character.charatersapp.characters_feature.presentation.ui.TextSizeLarge
import com.character.charatersapp.characters_feature.presentation.ui.TextSizeMedium
import com.character.charatersapp.characters_feature.presentation.ui.getHouseColor

@Composable
fun CharacterDetail(modifier: Modifier = Modifier, character: Character) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(SmallMargin),
        shape = RoundedCornerShape(MediumMargin), modifier = modifier
            .padding(LargeMargin)
            .testTag("detail_card")
    ) {

        val houseColor: Color = getHouseColor(character.house)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(LargeMargin)
        ) {
            SpacerView(houseColor = houseColor)
            TextHolder(
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = TextSizeLarge,
                fontStyle = FontStyle.Normal
            )
            SpacerView(houseColor = houseColor)
            DrawImageComposable(imageUrl = character.image)
            SpacerView(houseColor = houseColor)
            TextHolder(
                text = character.actor,
                fontWeight = FontWeight.Bold,
                fontSize = TextSizeLarge,
                fontStyle = FontStyle.Normal
            )
            TextHolder(
                text = character.species,
                fontWeight = FontWeight.Bold,
                fontSize = TextSizeMedium,
                fontStyle = FontStyle.Normal
            )
            DisplayFormattedDate(character.dateOfBirth.toString())
            TextHolder(
                text = if (character.alive) ALIVE else DEAD,
                fontWeight = FontWeight.Bold,
                fontSize = TextSizeMedium,
                fontStyle = FontStyle.Normal
            )
            SpacerView(houseColor = houseColor)
        }
    }
}