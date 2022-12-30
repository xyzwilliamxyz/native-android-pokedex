package com.example.pokedex.presentation.pokemonlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.common.CoroutineDispatcherProvider
import com.example.pokedex.domain.interactor.PokemonUseCases
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.pokemonlist.viewmodel.PokemonListViewModel.UIState.Loaded
import com.example.pokedex.presentation.pokemonlist.viewmodel.PokemonListViewModel.UIState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases,
    private val dispatcher: CoroutineDispatcherProvider
): ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
    val uiState: StateFlow<UIState> = _uiState

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        _uiState.value = Loading
        viewModelScope.launch(dispatcher.io()) {
            runCatching { pokemonUseCases.getPokemonList() }
                .onSuccess { data ->
                    _uiState.value = Loaded(data)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    sealed class UIState {
        object Idle : UIState()
        object Loading : UIState()
        data class Loaded(val data: List<Pokemon>) : UIState()
        data class Error(val message: String) : UIState()
    }
}