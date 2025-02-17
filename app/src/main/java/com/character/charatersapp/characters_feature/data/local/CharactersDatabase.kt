package com.character.charatersapp.characters_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.character.charatersapp.characters_feature.data.local.dao.CharactersDao
import com.character.charatersapp.characters_feature.data.local.entity.CharactersEntity

@Database(
    entities = [CharactersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun getCharacterDao(): CharactersDao
}