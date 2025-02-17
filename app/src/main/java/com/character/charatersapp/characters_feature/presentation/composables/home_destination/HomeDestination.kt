package com.character.charatersapp.characters_feature.presentation.composables.home_destination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.character.charatersapp.characters_feature.domain.model.Character
import com.character.charatersapp.characters_feature.presentation.composables.util.CharacterItem
import com.character.charatersapp.characters_feature.presentation.composables.util.ErrorMessageText
import com.character.charatersapp.characters_feature.presentation.composables.util.ProgressBar
import com.character.charatersapp.characters_feature.presentation.state.CharactersState
import com.character.charatersapp.characters_feature.presentation.ui.LargeMargin
import com.character.charatersapp.characters_feature.presentation.ui.MediumMargin
import com.character.charatersapp.characters_feature.presentation.ui.SEARCH_PLACEHOLDER

@Composable
fun HomeDestination(
    modifier: Modifier = Modifier,
    charactersState: CharactersState,
    query: String,
    onClickNavigate: (character: Character) -> Unit,
    searchQuery: (query: String) -> Unit
) {
    if (charactersState.isLoading) {
        ProgressBar()
    } else if (!charactersState.errorMsg.isNullOrEmpty()) {
        ErrorMessageText(errorMsg = charactersState.errorMsg)
    } else {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(LargeMargin)
        ) {
            TextField(value = query, onValueChange = {
                searchQuery(it)
            }, placeholder = {
                Text(SEARCH_PLACEHOLDER)
            }, modifier = modifier.padding(MediumMargin).testTag("search_query"))

            LazyColumn {
                items(charactersState.characterData) { character ->
                    CharacterItem(character = character) { characterItem ->
                        onClickNavigate(characterItem)
                    }
                }
            }
        }
    }
}