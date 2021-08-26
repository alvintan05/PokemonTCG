package com.aldev.pokemontcg.data.source

import com.aldev.pokemontcg.data.source.remote.response.Card

interface PokemonCardDataSource {
    suspend fun getPokemons(set: String): MutableList<Card>?
    suspend fun addAll(sets: String, pokemons: MutableList<Card>?)
}