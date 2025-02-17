package com.character.charatersapp.characters_feature.util

import com.character.charatersapp.characters_feature.data.local.entity.CharactersEntity
import com.character.charatersapp.characters_feature.data.remote.dto.CharactersDto
import com.character.charatersapp.characters_feature.data.remote.dto.WandDto
import com.character.charatersapp.characters_feature.domain.model.Character

open class TestDataHolder {

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
        )
    )

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
        )
    )

    val charactersEntitySave = listOf<CharactersEntity>(
        CharactersEntity(
            id = "2",
            actor = "Harmaione",
            name = "Emma Watson",
            species = "Human",
            alive = true,
            image = "https://ik.imagekit.io/hpapi/hermione.jpeg",
            dateOfBirth = "20-02-1991",
            house = "Red")
    )

    val charactersDto = listOf<CharactersDto>(
        CharactersDto(
            id = "2",
            actor = "Harmaione",
            name = "Emma Watson",
            species = "Human",
            alive = true,
            image = "https://ik.imagekit.io/hpapi/hermione.jpeg",
            dateOfBirth = "20-02-1991",
            house = "Red",
            alternateActors = emptyList(),
            alternateNames = emptyList(),
            ancestry = "",
            eyeColour = "",
            gender = "female",
            hairColour = "",
            hogwartsStaff = true,
            hogwartsStudent = false,
            patronus = "",
            wizard = false,
            wand = WandDto()
        )
    )


    val detailScreenCharacter = Character(
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