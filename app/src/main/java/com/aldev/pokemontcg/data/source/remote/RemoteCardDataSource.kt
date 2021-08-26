package com.aldev.pokemontcg.data.source.remote

import com.aldev.pokemontcg.data.source.DataSource
import com.aldev.pokemontcg.data.source.PokemonCardDataSource
import com.aldev.pokemontcg.data.source.remote.response.Card
import com.aldev.pokemontcg.data.source.remote.response.Set

class RemoteCardDataSource(private val service: ApiRequest) : PokemonCardDataSource {
    override suspend fun getPokemons(set: String): MutableList<Card>? {
        val setQuery = "set.id:$set"
        val response = service.getCarsBySetsId(setQuery)
        if (response.isSuccessful) return response.body()?.data

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: String, pokemons: MutableList<Card>?) {
        TODO("Not yet implemented")
    }

}