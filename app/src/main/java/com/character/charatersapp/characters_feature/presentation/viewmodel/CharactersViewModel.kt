package com.character.charatersapp.characters_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.character.charatersapp.characters_feature.core.common.Resource
import com.character.charatersapp.characters_feature.domain.model.Character
import com.character.charatersapp.characters_feature.domain.usecase.GetCharactersUseCase
import com.character.charatersapp.characters_feature.domain.usecase.SearchCharactersUseCase
import com.character.charatersapp.characters_feature.presentation.event.UiEvent
import com.character.charatersapp.characters_feature.presentation.state.CharacterDetailState
import com.character.charatersapp.characters_feature.presentation.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: GetCharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val _charactersState = MutableStateFlow(CharactersState())
    private val _characterDetailState = MutableStateFlow(CharacterDetailState())

    val charactersState: Flow<CharactersState> = _charactersState.asStateFlow()
    val characterDetailState: Flow<CharacterDetailState> = _characterDetailState.asStateFlow()

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = charactersUseCase().onEach { resource ->
        handleCharacterState(resource)
    }.launchIn(viewModelScope)

    private fun searchCharacters(query: String) = searchCharactersUseCase(query)
        .onEach { resource ->
            handleCharacterState(resource)
        }.launchIn(viewModelScope)

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.NavigateToDetailScreen -> {
                _characterDetailState.value = _characterDetailState.value.copy(
                    character = uiEvent.character
                )
            }

            is UiEvent.SearchCharactersQuery -> {
                val query = uiEvent.query.trim()
                if (query.isEmpty()) {
                    getAllCharacters()
                } else {
                    searchCharacters(query)
                }
            }
        }
    }

    private fun handleCharacterState(resource: Resource<List<Character>>) {
        when (resource) {
            is Resource.Failure -> {
                _charactersState.value = _charactersState.value.copy(
                    characterData = emptyList(),
                    errorMsg = resource.errorMsg,
                    isLoading = false
                )
            }

            is Resource.Loading -> {
                _charactersState.value = _charactersState.value.copy(
                    characterData = emptyList(),
                    errorMsg = "",
                    isLoading = true
                )
            }

            is Resource.Success -> {
                _charactersState.value = _charactersState.value.copy(
                    characterData = resource.data,
                    errorMsg = "",
                    isLoading = false
                )
            }
        }
    }

}