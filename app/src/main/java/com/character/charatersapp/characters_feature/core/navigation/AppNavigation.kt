package com.character.charatersapp.characters_feature.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.character.charatersapp.characters_feature.presentation.composables.detail_destination.DetailDestination
import com.character.charatersapp.characters_feature.presentation.composables.home_destination.HomeDestination
import com.character.charatersapp.characters_feature.presentation.event.UiEvent
import com.character.charatersapp.characters_feature.core.navigation.screen.CharactersDestinations
import com.character.charatersapp.characters_feature.presentation.state.CharacterDetailState
import com.character.charatersapp.characters_feature.presentation.state.CharactersState
import com.character.charatersapp.characters_feature.presentation.viewmodel.CharactersViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    charactersViewModel: CharactersViewModel = hiltViewModel()
) {
    val query = remember { mutableStateOf("") }
    NavHost(
        navController = navController,
        startDestination = CharactersDestinations.HomeDestination.route
    ) {

        composable(CharactersDestinations.HomeDestination.route) {
            val charactersState =
                charactersViewModel.charactersState.collectAsStateWithLifecycle(CharactersState()).value

            HomeDestination(
                modifier = modifier,
                charactersState = charactersState,
                query = query.value,
                onClickNavigate = { character ->
                    charactersViewModel.onEvent(UiEvent.NavigateToDetailScreen(character))
                    navController.navigate(CharactersDestinations.DetailDestination.route)
                }) {
                query.value = it
                charactersViewModel.onEvent(UiEvent.SearchCharactersQuery(it))
            }
        }

        composable(CharactersDestinations.DetailDestination.route) {
            val detailState = charactersViewModel.characterDetailState.collectAsStateWithLifecycle(
                initialValue = CharacterDetailState()
            ).value
            DetailDestination(modifier = modifier, detailState.character) {
                navController.popBackStack()
            }
        }

    }

}