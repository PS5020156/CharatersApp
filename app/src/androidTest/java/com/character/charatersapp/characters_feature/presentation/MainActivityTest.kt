package com.character.charatersapp.characters_feature.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.character.charatersapp.characters_feature.core.navigation.screen.CharactersDestinations
import com.character.charatersapp.characters_feature.presentation.composables.home_destination.HomeDestination
import com.character.charatersapp.characters_feature.presentation.composables.util.BackButtonHeader
import com.character.charatersapp.characters_feature.presentation.composables.util.CharacterDetail
import com.character.charatersapp.characters_feature.presentation.composables.util.CharacterItem
import com.character.charatersapp.characters_feature.presentation.state.CharactersState
import com.character.charatersapp.characters_feature.utils.InstrumentationTestData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MainActivityTest : InstrumentationTestData() {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchHomeScreen() {
        composeTestRule.setContent {
            CharacterItem(character = character, onClickNavigate = {})
        }
        composeTestRule.onNodeWithText("Search Character").isDisplayed()
        composeTestRule.onNodeWithText("Character Name").isDisplayed()
    }



    @Test
    fun testSearchQuery_updatesWhenTyping() {
        val loadedState = CharactersState(isLoading = false, characterData = characters, errorMsg = null)
        composeTestRule.setContent {
            HomeDestination(
                charactersState = loadedState,
                query = "",
                onClickNavigate = {},
                searchQuery = {}
            )
        }
        composeTestRule.onNodeWithTag("search_query").performTextInput("Harry")
        composeTestRule.onNodeWithText("Harry").isDisplayed()
    }


    @Test
    fun testCharacterListItemContent() {
        composeTestRule.setContent {
            CharacterItem(character = character, onClickNavigate = {})
        }
        composeTestRule.onNodeWithText("Character Name").isDisplayed()
        composeTestRule.onNodeWithText("Actor Name").isDisplayed()
        composeTestRule.onNodeWithText("Species Name").isDisplayed()
        composeTestRule.onNodeWithTag("characterItem").assertExists()
    }

    @Test
    fun testHomeToDetailScreenNavigation() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            CharacterDetail(character = character)
            CharacterItem(character = character, onClickNavigate = {
                navController.navigate(CharactersDestinations.DetailDestination.route)
                composeTestRule.onNodeWithText("Character Name").isDisplayed()
                composeTestRule.onNodeWithText("Actor Name").isDisplayed()
                composeTestRule.onNodeWithText("Species Name").isDisplayed()
                composeTestRule.onNodeWithTag("detail_card").isDisplayed()
                composeTestRule.onNodeWithTag(character.dateOfBirth.toString()).isDisplayed()
                composeTestRule.onNodeWithTag(character.house).isDisplayed()
                composeTestRule.onNodeWithTag(character.alive.toString()).isDisplayed()

            })
        }
    }

    @Test
    fun testNavigationDetailScreenToHome() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            CharacterDetail(character = character)

            CharacterItem(character = character, onClickNavigate = {
                navController.navigate(CharactersDestinations.DetailDestination.route)
            })
            BackButtonHeader(header = "") {}
        }
        composeTestRule.onNodeWithTag("back_click").performClick()
        composeTestRule.onNodeWithText("Character Name").isDisplayed()
        composeTestRule.onNodeWithText("Actor Name").isDisplayed()
        composeTestRule.onNodeWithText("Species Name").isDisplayed()
    }

}