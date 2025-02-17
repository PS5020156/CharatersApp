package com.character.charatersapp.characters_feature.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.character.charatersapp.characters_feature.data.local.CharactersDatabase
import com.character.charatersapp.characters_feature.utils.InstrumentationTestData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterDaoTest : InstrumentationTestData() {

    private lateinit var charactersDatabase: CharactersDatabase
    private lateinit var charactersDao: CharactersDao

    @Before
    fun setUp() {
        charactersDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharactersDatabase::class.java
        ).allowMainThreadQueries().build()
        charactersDao = charactersDatabase.getCharacterDao()
    }


    @Test
    fun insertCharacter_expectedNonEmptyList() = runTest {
        charactersDao.insertCharacters(charactersEntity)
        val result = charactersDao.getAllCharacters()
        Assert.assertEquals(result.size, 2)
        Assert.assertTrue(result.isNotEmpty())
    }

    @Test
    fun getCharacterByQuery_expectedSingleItem() = runTest {
        charactersDao.insertCharacters(charactersEntity)
        val result = charactersDao.getCharactersByActorOrName("Harry")
        Assert.assertEquals(result.size, 1)
    }

    @Test
    fun deleteCharacter_expectedEmptyList() = runTest {
        charactersDao.insertCharacters(charactersEntity)
        charactersDao.deleteCharacters()
        val result = charactersDao.getAllCharacters()
        Assert.assertTrue(result.isEmpty())
    }


    @After
    fun tearDown() {
        charactersDatabase.close()
    }

}