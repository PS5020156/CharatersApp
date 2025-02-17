package com.character.charatersapp.characters_feature.domain.usecase

import com.character.charatersapp.characters_feature.core.utils.Constants.DEBOUNCE_TIMEOUT
import com.character.charatersapp.characters_feature.domain.repository.CharactersRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject


class SearchCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    @OptIn(FlowPreview::class)
    operator fun invoke(query: String) =
        charactersRepository
            .searchCharactersStream(query)
            .debounce(DEBOUNCE_TIMEOUT)
}