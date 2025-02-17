package com.character.charatersapp.characters_feature.di

import android.content.Context
import androidx.room.Room
import com.character.charatersapp.characters_feature.core.utils.Constants.CHARACTER_DATABASE
import com.character.charatersapp.characters_feature.data.local.CharactersDatabase
import com.character.charatersapp.characters_feature.data.local.dao.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharactersDatabase(@ApplicationContext appContext: Context): CharactersDatabase {
        return Room.databaseBuilder(
            appContext,
            CharactersDatabase::class.java,
            CHARACTER_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideCharactersDao(charactersDatabase: CharactersDatabase): CharactersDao {
        return charactersDatabase.getCharacterDao()
    }

}