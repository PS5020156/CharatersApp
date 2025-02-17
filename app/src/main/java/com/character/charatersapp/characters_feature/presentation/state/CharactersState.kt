package com.character.charatersapp.characters_feature.presentation.state

import com.character.charatersapp.characters_feature.domain.model.Character

data class CharactersState(
    val characterData: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = ""
)
