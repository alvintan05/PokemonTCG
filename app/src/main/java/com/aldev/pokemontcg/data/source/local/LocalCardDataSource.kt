package com.aldev.pokemontcg.data.source.local

import com.aldev.pokemontcg.data.source.PokemonCardDataSource
import com.aldev.pokemontcg.data.source.remote.response.Card

class LocalCardDataSource : PokemonCardDataSource {
    private var caches = mutableMapOf<String, MutableList<Card>?>()

    override suspend fun getPokemons(set: String): MutableList<Card>? =
        if (caches.contains(set)) caches[set] else null

    override suspend fun addAll(sets: String, pokemons: MutableList<Card>?) {
        caches[sets] = pokemons
    }
}