package com.aldev.pokemontcg.ui.detailcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldev.pokemontcg.data.source.remote.response.Card
import com.aldev.pokemontcg.utils.PokemonDetailCardViewState

class PokemonCardDetailViewModel : ViewModel() {
    val viewState: LiveData<PokemonDetailCardViewState>
        get() = mViewState

    private val mViewState = MutableLiveData<PokemonDetailCardViewState>().apply {
        value = PokemonDetailCardViewState(null)
    }

    fun setData(pokemonCard: Card) {
        mViewState.value = mViewState.value?.copy(data = pokemonCard)
    }
}