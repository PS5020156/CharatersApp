package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.character.charatersapp.characters_feature.presentation.ui.SmallMargin

@Composable
fun SpacerView(modifier: Modifier = Modifier, houseColor: Color = Color.Magenta) {
    Spacer(
    modifier = modifier
    .fillMaxWidth()
    .padding(SmallMargin)
    .height(SmallMargin)
    .background(color = houseColor)
    )
}