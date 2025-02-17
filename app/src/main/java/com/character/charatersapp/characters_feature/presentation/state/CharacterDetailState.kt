package com.character.charatersapp.characters_feature.presentation.state

import com.character.charatersapp.characters_feature.domain.model.Character

data class CharacterDetailState(
    val character: Character = Character(),
    val isLoading: Boolean = false,
    val errorMsg: String? = ""
)