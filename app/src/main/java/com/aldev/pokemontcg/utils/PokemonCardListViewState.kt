package com.aldev.pokemontcg.utils

import com.aldev.pokemontcg.data.source.remote.response.Card

data class PokemonCardListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<Card>? = null
)