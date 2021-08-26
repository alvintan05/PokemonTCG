package com.aldev.pokemontcg.ui.sets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldev.pokemontcg.data.PokemonSetRepository
import com.aldev.pokemontcg.utils.MainViewState
import kotlinx.coroutines.launch

class SetListViewModel(
    private val pokemonSets: PokemonSetRepository
) : ViewModel() {

    val viewState: LiveData<MainViewState>
        get() = mViewState

    private val mViewState = MutableLiveData<MainViewState>().apply {
        value = MainViewState(loading = true)
    }

    init {
        getSets()
    }

    fun getSets() = viewModelScope.launch {
        try {
            val data = pokemonSets.getSets()
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }

}