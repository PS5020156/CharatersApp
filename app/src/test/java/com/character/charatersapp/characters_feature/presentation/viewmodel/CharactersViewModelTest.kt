package com.character.charatersapp.characters_feature.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.character.charatersapp.characters_feature.core.common.Resource
import com.character.charatersapp.characters_feature.domain.usecase.GetCharactersUseCase
import com.character.charatersapp.characters_feature.domain.usecase.SearchCharactersUseCase
import com.character.charatersapp.characters_feature.presentation.event.UiEvent
import com.character.charatersapp.characters_feature.util.MainCoroutineRule
import com.character.charatersapp.characters_feature.util.TestDataHolder
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest : TestDataHolder() {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Mock
    private lateinit var charactersUseCase: GetCharactersUseCase

    @Mock
    private lateinit var searchCharactersUseCase: SearchCharactersUseCase

    private lateinit var charactersViewModel: CharactersViewModel


    @Before
    fun setUp() {

    }


    @Test
    fun `get all character test`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(emptyList()))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertTrue(result.characterData.isEmpty())
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(1)).invoke()
        }
    }

    @Test
    fun `should get success character with count 1`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertTrue(result.characterData.isNotEmpty())
            Assert.assertEquals("1", result.characterData[0].id)
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(1)).invoke()
        }
    }

    @Test
    fun `should get error In case of exception`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Failure("Something went wrong!"))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertEquals("Something went wrong!", result.errorMsg)
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(1)).invoke()
        }
    }


    @Test
    fun `should get result in case of empty search`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.onEvent(UiEvent.SearchCharactersQuery(""))
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertTrue(result.characterData.isNotEmpty())
            Assert.assertEquals("1", result.characterData[0].id)
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(2)).invoke()
        }
    }

    @Test
    fun `should get result in case of valid search`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        whenever(searchCharactersUseCase("Harry")).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        charactersViewModel.onEvent(UiEvent.SearchCharactersQuery("Harry"))
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertTrue(result.characterData.isNotEmpty())
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(1)).invoke()
            verify(searchCharactersUseCase, times(1)).invoke("Harry")
        }
    }


    @Test
    fun `should get no result in case of in valid search`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        whenever(searchCharactersUseCase("Hello")).thenReturn(
            flow {
                emit(Resource.Failure("Something went wrong"))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        charactersViewModel.onEvent(UiEvent.SearchCharactersQuery("Hello"))
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.charactersState.test {
            val result = awaitItem()
            Assert.assertEquals("Something went wrong", result.errorMsg)
            cancelAndIgnoreRemainingEvents()
            verify(charactersUseCase, times(1)).invoke()
            verify(searchCharactersUseCase, times(1)).invoke("Hello")
        }
    }


    @Test
    fun `should get detail Screen data in case of navigation`() = runTest {
        whenever(charactersUseCase()).thenReturn(
            flow {
                emit(Resource.Success(characters))
            }
        )
        charactersViewModel = CharactersViewModel(charactersUseCase, searchCharactersUseCase)
        charactersViewModel.onEvent(UiEvent.NavigateToDetailScreen(detailScreenCharacter))
        mainCoroutineRule.coroutineDispatcher.scheduler.advanceUntilIdle()
        charactersViewModel.characterDetailState.test {
            val result = awaitItem()
            Assert.assertEquals(result.character.id, "1")
        }
    }


    @After
    fun tearDown() {

    }

}