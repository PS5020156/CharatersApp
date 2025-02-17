package com.character.charatersapp.characters_feature.domain.usecase

import com.character.charatersapp.characters_feature.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    operator fun invoke() = charactersRepository.getAllCharactersStream()
}