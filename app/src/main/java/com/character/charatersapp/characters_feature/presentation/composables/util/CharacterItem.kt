package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.character.charatersapp.characters_feature.domain.model.Character
import com.character.charatersapp.characters_feature.presentation.ui.ACTOR_NAME
import com.character.charatersapp.characters_feature.presentation.ui.CHARACTER_NAME
import com.character.charatersapp.characters_feature.presentation.ui.MediumMargin
import com.character.charatersapp.characters_feature.presentation.ui.SPECIES_TAG
import com.character.charatersapp.characters_feature.presentation.ui.SmallMargin
import com.character.charatersapp.characters_feature.presentation.ui.TextSizeMedium
import com.character.charatersapp.characters_feature.presentation.ui.getHouseColor

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    onClickNavigate: (character: Character) -> Unit
) {
    val houseColor: Color = getHouseColor(character.house)
    Card(
        elevation = CardDefaults.elevatedCardElevation(SmallMargin),
        shape = RectangleShape,
        modifier = modifier
            .padding(MediumMargin)
            .testTag("characterItem")
            .clickable { onClickNavigate(character) },
        border = BorderStroke(SmallMargin, houseColor)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(MediumMargin)
                .fillMaxWidth()
        ) {

            TextHolder(
                tag = CHARACTER_NAME,
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = TextSizeMedium,
                fontStyle = FontStyle.Normal
            )

            TextHolder(
                tag = ACTOR_NAME,
                text = character.actor,
                fontWeight = FontWeight.Medium,
                fontSize = TextSizeMedium,
                fontStyle = FontStyle.Normal
            )

            TextHolder(
                tag = SPECIES_TAG,
                text = character.species,
                fontWeight = FontWeight.Light,
                fontSize = TextSizeMedium,
                fontStyle = FontStyle.Italic
            )

            SpacerView(houseColor = houseColor)
        }
    }
}