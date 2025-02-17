package com.character.charatersapp.characters_feature.core.exception

class CharacterException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    private val errorCode: Int? = null
    override fun toString(): String {
        return "CharacterException(errorCode=$errorCode, message=${super.message}, cause=${super.cause})"
    }
}