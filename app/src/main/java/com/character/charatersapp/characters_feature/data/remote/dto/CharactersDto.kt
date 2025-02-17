package com.character.charatersapp.characters_feature.data.remote.dto

import com.character.charatersapp.characters_feature.data.local.entity.CharactersEntity
import com.google.gson.annotations.SerializedName

data class CharactersDto(
    val actor: String,
    val alive: Boolean = false,
    @SerializedName("alternate_actors")
    val alternateActors: List<String>,
    @SerializedName("alternate_names")
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String? = null,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: WandDto,
    val wizard: Boolean,
    val yearOfBirth: Int? = null
)

fun CharactersDto.toEntityCharacters(): CharactersEntity {
    return CharactersEntity(
        id = id,
        name = name,
        actor = actor,
        species = species,
        alive = alive,
        image = image,
        dateOfBirth = dateOfBirth,
        house = house
    )
}