package com.example.pokedex.core.common.exception

class BackendException(
    val errorType: String? = null,
    errorMessage: String? = null,
) : RuntimeException(errorMessage)
