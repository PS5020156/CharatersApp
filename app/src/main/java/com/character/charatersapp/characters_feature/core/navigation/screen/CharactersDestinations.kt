package com.character.charatersapp.characters_feature.core.navigation.screen

import com.character.charatersapp.characters_feature.core.utils.Constants.DETAIL_DESTINATION
import com.character.charatersapp.characters_feature.core.utils.Constants.HOME_DESTINATION

sealed class CharactersDestinations(val route: String) {
    data object HomeDestination :
        CharactersDestinations(HOME_DESTINATION)

    data object DetailDestination :
        CharactersDestinations(DETAIL_DESTINATION)
}