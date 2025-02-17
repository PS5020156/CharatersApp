package com.character.charatersapp.characters_feature.di

import com.character.charatersapp.characters_feature.data.local.dao.CharactersDao
import com.character.charatersapp.characters_feature.data.remote.api.CharactersAPI
import com.character.charatersapp.characters_feature.data.repository.DefaultCharactersRepository
import com.character.charatersapp.characters_feature.di.qualifier.IODispatcher
import com.character.charatersapp.characters_feature.di.qualifier.MainDispatcher
import com.character.charatersapp.characters_feature.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(
        charactersAPI: CharactersAPI,
        charactersDao: CharactersDao,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): CharactersRepository {
        return DefaultCharactersRepository(charactersAPI, charactersDao, ioDispatcher)
    }

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher() = Dispatchers.Main

}