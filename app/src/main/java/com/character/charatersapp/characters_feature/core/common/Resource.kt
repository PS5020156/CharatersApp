package com.character.charatersapp.characters_feature.core.common

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val errorMsg: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}