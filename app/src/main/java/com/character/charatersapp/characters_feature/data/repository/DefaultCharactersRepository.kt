package com.character.charatersapp.characters_feature.data.repository

import com.character.charatersapp.characters_feature.core.common.Resource
import com.character.charatersapp.characters_feature.core.exception.CharacterException
import com.character.charatersapp.characters_feature.core.utils.Constants.GENERIC_ERROR
import com.character.charatersapp.characters_feature.data.local.dao.CharactersDao
import com.character.charatersapp.characters_feature.data.local.entity.toDomainCharacters
import com.character.charatersapp.characters_feature.data.remote.api.CharactersAPI
import com.character.charatersapp.characters_feature.data.remote.dto.toEntityCharacters
import com.character.charatersapp.characters_feature.di.qualifier.IODispatcher
import com.character.charatersapp.characters_feature.domain.model.Character
import com.character.charatersapp.characters_feature.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject


class DefaultCharactersRepository @Inject constructor(
    private val charactersAPI: CharactersAPI,
    private val charactersDao: CharactersDao,
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher
) : CharactersRepository {

    override fun getAllCharactersStream(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        val cachedResult = charactersDao.getAllCharacters().map { it.toDomainCharacters() }
        if (cachedResult.isNotEmpty()) {
            emit(Resource.Success(cachedResult))
        }

        try {
            val remoteCharacters = charactersAPI.getAllCharacters().map {
                it.toEntityCharacters()
            }
            charactersDao.deleteCharacters()
            charactersDao.insertCharacters(remoteCharacters)
        } catch (unKnownException: UnknownHostException) {
            emit(Resource.Failure("No internet connection. Please try again later."))
        } catch (httpException: HttpException) {
            emit(Resource.Failure("Failed to load data. Please try again later."))
        } catch (e: Exception) {
            emit(Resource.Failure(GENERIC_ERROR))
            throw CharacterException(message = e.message, cause = e.cause)
        }
        val updatedCharacters = charactersDao.getAllCharacters().map { it.toDomainCharacters() }
        emit(Resource.Success(updatedCharacters))
    }.flowOn(coroutineDispatcher)
        .catch {
            emit(Resource.Failure(GENERIC_ERROR + it.message))
        }

    override fun searchCharactersStream(query: String): Flow<Resource<List<Character>>> =
        flow {
            emit(Resource.Loading())
            val cachedQuery = charactersDao.getCharactersByActorOrName(query)
                .map {
                    it.toDomainCharacters()
                }
            if (cachedQuery.isNotEmpty()) {
                emit(Resource.Success(cachedQuery))
            } else {
                emit(Resource.Success(emptyList()))
            }
        }.flowOn(coroutineDispatcher)
            .catch {
                emit(Resource.Failure(GENERIC_ERROR + it.message))
            }
}