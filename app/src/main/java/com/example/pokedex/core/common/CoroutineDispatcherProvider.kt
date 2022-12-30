package com.example.pokedex.core.common

import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider {

    fun io() = Dispatchers.IO

    fun default() = Dispatchers.Default

    fun main() = Dispatchers.Main
}