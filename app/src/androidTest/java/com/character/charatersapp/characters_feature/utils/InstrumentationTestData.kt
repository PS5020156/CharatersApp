package com.character.charatersapp.characters_feature.utils

import com.character.charatersapp.characters_feature.data.local.entity.CharactersEntity
import com.character.charatersapp.characters_feature.domain.model.Character

open class InstrumentationTestData {

    val characters = listOf<Character>(
        Character(
            "1",
            "Harry Potter",
            "Daniel Radcliffe",
            "Human",
            true,
            "https://ik.imagekit.io/hpapi/hermione.jpeg",
            "20-02-1991",
            "Red"
        ),
        Character(
            "2",
            "Harmaine",
            "Emma Watson",
            "Human",
            true,
            "https://ik.imagekit.io/hpapi/hermione.jpeg",
            "20-02-1991",
            "Red"
        )
    )

    val charactersEntity = listOf<CharactersEntity>(
        CharactersEntity(
            "1",
            "Harry Potter",
            "Daniel Radcliffe",
            "Human",
            true,
            "https://ik.imagekit.io/hpapi/hermione.jpeg",
            "20-02-1991",
            "Red"
        ),
        CharactersEntity(
            "2",
            "Harmaine",
            "Emma Watson",
            "Human",
            true,
            "https://ik.imagekit.io/hpapi/hermione.jpeg",
            "20-02-1991",
            "Red"
        )
    )

    val character = Character(
        "1",
        "Harry Potter",
        "Daniel Radcliffe",
        "Human",
        true,
        "https://ik.imagekit.io/hpapi/hermione.jpeg",
        "20-02-1991",
        "Red"
    )
}