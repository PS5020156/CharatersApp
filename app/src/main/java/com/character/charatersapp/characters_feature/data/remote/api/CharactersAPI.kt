package com.character.charatersapp.characters_feature.data.remote.api

import com.character.charatersapp.characters_feature.data.remote.dto.CharactersDto
import retrofit2.http.GET

interface CharactersAPI {

    companion object {
        const val CHARACTERS_ENDPOINT = "characters"
    }

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getAllCharacters(): List<CharactersDto>
}