package com.aldev.pokemontcg.data

import com.aldev.pokemontcg.data.source.PokemonCardDataSource
import com.aldev.pokemontcg.data.source.remote.response.Card

class PokemonCardRepository private constructor() : BaseRepository<PokemonCardDataSource>() {
    suspend fun getPokemons(set: String): MutableList<Card>? {
        val cache = localDataSource?.getPokemons(set)
        if (cache != null) return cache

        val response = remoteDataSource?.getPokemons(set)
        localDataSource?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}