package com.character.charatersapp.characters_feature.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.character.charatersapp.characters_feature.core.common.Resource
import com.character.charatersapp.characters_feature.data.local.dao.CharactersDao
import com.character.charatersapp.characters_feature.data.remote.api.CharactersAPI
import com.character.charatersapp.characters_feature.domain.repository.CharactersRepository
import com.character.charatersapp.characters_feature.util.MainCoroutineRule
import com.character.charatersapp.characters_feature.util.TestDataHolder
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
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
class DefaultCharactersRepositoryTest : TestDataHolder() {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var charactersAPI: CharactersAPI

    @Mock
    private lateinit var charactersDao: CharactersDao

    private lateinit var characterRepository: CharactersRepository

    @Before
    fun setUp() {
        characterRepository = DefaultCharactersRepository(
            charactersAPI,
            charactersDao,
            mainCoroutineRule.coroutineDispatcher
        )
    }

    @Test
    fun `should return single result from table, after network call`() = runTest {
        whenever(charactersDao.getAllCharacters()).thenReturn(charactersEntity)

        characterRepository.getAllCharactersStream().test {
            // Assert loading state first
            assertTrue(awaitItem() is Resource.Loading)

            // Assert success state with valid character data
            val character = awaitItem()
            assertTrue(character is Resource.Success)
            character as Resource.Success
            assertTrue(character.data.isNotEmpty())
            assertEquals("1", character.data[0].id)

            // Cleanup
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should save network result from table, after network call`() = runTest {
        // Arrange mocks
        whenever(charactersDao.getAllCharacters()).thenReturn(charactersEntity)
        whenever(charactersAPI.getAllCharacters()).thenReturn(charactersDto)

        characterRepository.getAllCharactersStream().test {
            // Assert loading state
            assertTrue(awaitItem() is Resource.Loading)

            // Assert success state twice (first from Dao, second from API)
            assertTrue(awaitItem() is Resource.Success)
            assertTrue(awaitItem() is Resource.Success)

            awaitComplete()
        }

        // Verify API and DAO calls
        verify(charactersDao, times(2)).getAllCharacters()
        verify(charactersDao, times(1)).deleteCharacters()
        verify(charactersDao, times(1)).insertCharacters(charactersEntitySave)
        verify(charactersAPI, times(1)).getAllCharacters()
    }

    @Test
    fun `should return network result on empty database`() = runTest {
        whenever(charactersDao.getAllCharacters()).thenReturn(emptyList())
        whenever(charactersAPI.getAllCharacters()).thenReturn(charactersDto)

        characterRepository.getAllCharactersStream().test {
            assertTrue(awaitItem() is Resource.Loading)
            assertTrue(awaitItem() is Resource.Success)
            awaitComplete()
        }
    }

    @Test
    fun `should return failure result on exception`() = runTest {
        whenever(charactersDao.getAllCharacters()).thenReturn(emptyList())
        whenever(charactersAPI.getAllCharacters()).thenThrow(RuntimeException())

        characterRepository.getAllCharactersStream().test {
            assertTrue(awaitItem() is Resource.Loading)
            assertTrue(awaitItem() is Resource.Failure)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should return empty list on invalid search query`() = runTest {
        whenever(charactersDao.getCharactersByActorOrName("")).thenReturn(emptyList())

        characterRepository.searchCharactersStream("").test {
            assertTrue(awaitItem() is Resource.Loading)
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            result as Resource.Success
            assertTrue(result.data.isEmpty())

            // Verify DAO method called
            verify(charactersDao, times(1)).getCharactersByActorOrName("")

            awaitComplete()
        }
    }

    @Test
    fun `should return success result on valid search query`() = runTest {
        whenever(charactersDao.getCharactersByActorOrName("Harry")).thenReturn(charactersEntity)

        characterRepository.searchCharactersStream("Harry").test {
            assertTrue(awaitItem() is Resource.Loading)
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            result as Resource.Success
            assertTrue(result.data.isNotEmpty())
            assertEquals("1", result.data[0].id)

            // Verify DAO method called
            verify(charactersDao, times(1)).getCharactersByActorOrName("Harry")

            awaitComplete()
        }
    }

    @After
    fun tearDown() {
        // Cleanup if needed
    }
}
