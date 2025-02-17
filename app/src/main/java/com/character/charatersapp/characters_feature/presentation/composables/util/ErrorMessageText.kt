package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.character.charatersapp.characters_feature.presentation.ui.TextSizeMedium

@Composable
fun ErrorMessageText(modifier: Modifier = Modifier, errorMsg: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = errorMsg,
            fontWeight = FontWeight.Medium,
            fontSize = TextSizeMedium,
            fontStyle = FontStyle.Normal
        )
    }
}