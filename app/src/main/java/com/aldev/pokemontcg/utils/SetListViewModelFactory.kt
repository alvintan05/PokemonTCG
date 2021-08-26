package com.aldev.pokemontcg.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aldev.pokemontcg.data.PokemonSetRepository
import com.aldev.pokemontcg.ui.sets.SetListViewModel

class SetListViewModelFactory(
    private val setRepository: PokemonSetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetListViewModel::class.java)) {
            return SetListViewModel(setRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}