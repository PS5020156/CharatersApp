package com.character.charatersapp.characters_feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.character.charatersapp.characters_feature.core.utils.Constants.CHARACTER_TABLE
import com.character.charatersapp.characters_feature.core.utils.Constants.DATE_OF_BIRTH
import com.character.charatersapp.characters_feature.core.utils.Constants.DEFAULT_VALUE
import com.character.charatersapp.characters_feature.domain.model.Character

@Entity(tableName = CHARACTER_TABLE)
data class CharactersEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = DEFAULT_VALUE,
    val name: String = DEFAULT_VALUE,
    val actor: String = DEFAULT_VALUE,
    val species: String = DEFAULT_VALUE,
    val alive: Boolean = false,
    val image: String = DEFAULT_VALUE,
    @ColumnInfo(DATE_OF_BIRTH)
    val dateOfBirth: String? = DEFAULT_VALUE,
    val house: String = DEFAULT_VALUE
)

fun CharactersEntity.toDomainCharacters(): Character {
    return Character(
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