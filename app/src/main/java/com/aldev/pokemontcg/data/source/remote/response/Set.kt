package com.aldev.pokemontcg.data.source.remote.response

data class Set(
    val id: String,
    val name: String,
    val images: Image
)


data class SetResponse(
    val data: MutableList<Set>
)
