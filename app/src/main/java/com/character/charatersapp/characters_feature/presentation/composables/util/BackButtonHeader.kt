package com.character.charatersapp.characters_feature.presentation.composables.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.character.charatersapp.characters_feature.presentation.ui.BACK
import com.character.charatersapp.characters_feature.presentation.ui.MediumMargin

@Composable
fun BackButtonHeader(modifier: Modifier = Modifier, header: String, onBackButtonClick: () -> Unit) {

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(MediumMargin),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                onBackButtonClick()
            }, modifier = modifier.testTag("back_click")) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = BACK)
            }
            Text(
                text = header,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.align(Alignment.CenterVertically)
            )
        }
    }
}