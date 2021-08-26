package com.aldev.pokemontcg.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldev.pokemontcg.data.PokemonCardRepository
import com.aldev.pokemontcg.utils.PokemonCardListViewState
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonCardRepository: PokemonCardRepository
) : ViewModel() {

    val viewStateCard: LiveData<PokemonCardListViewState>
        get() = mViewState

    private val mViewState = MutableLiveData<PokemonCardListViewState>().apply {
        value = PokemonCardListViewState(loading = true)
    }

    fun getPokemons(set: String) = viewModelScope.launch {
        try {
            val data = pokemonCardRepository.getPokemons(set)
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }

}