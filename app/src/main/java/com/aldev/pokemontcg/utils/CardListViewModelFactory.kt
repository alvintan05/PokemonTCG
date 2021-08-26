package com.aldev.pokemontcg.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aldev.pokemontcg.data.PokemonCardRepository
import com.aldev.pokemontcg.ui.cards.PokemonListViewModel

class CardListViewModelFactory(
    private val pokemonCardRepository: PokemonCardRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java))
            return PokemonListViewModel(pokemonCardRepository) as T
        throw IllegalArgumentException()
    }
}