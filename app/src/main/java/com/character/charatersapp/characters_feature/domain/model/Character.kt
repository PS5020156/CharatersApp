package com.character.charatersapp.characters_feature.domain.model

import com.character.charatersapp.characters_feature.core.utils.Constants.DEFAULT_VALUE

data class Character(
    val id: String = DEFAULT_VALUE,
    val name: String = DEFAULT_VALUE,
    val actor: String = DEFAULT_VALUE,
    val species: String = DEFAULT_VALUE,
    val alive: Boolean = false,
    val image: String = DEFAULT_VALUE,
    val dateOfBirth: String? = DEFAULT_VALUE,
    val house: String = DEFAULT_VALUE
)