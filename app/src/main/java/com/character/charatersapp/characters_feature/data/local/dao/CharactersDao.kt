package com.character.charatersapp.characters_feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.character.charatersapp.characters_feature.data.local.entity.CharactersEntity


@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters_table")
    suspend fun getAllCharacters(): List<CharactersEntity>

    @Query(
        """SELECT * FROM characters_table 
        where 
            (name LIKE '%' || :query || '%') 
        OR  (actor LIKE '%' || :query || '%')"""
    )
    suspend fun getCharactersByActorOrName(query: String): List<CharactersEntity>

    @Query("DELETE FROM characters_table")
    suspend fun deleteCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharactersEntity>)
}