package com.aldev.pokemontcg.utils

import com.aldev.pokemontcg.data.source.remote.response.Set

data class MainViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<Set>? = null
)
