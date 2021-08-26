package com.aldev.pokemontcg.data

import com.aldev.pokemontcg.data.source.DataSource
import com.aldev.pokemontcg.data.source.remote.response.Set

class PokemonSetRepository private constructor() : BaseRepository<DataSource<Set>>() {
    suspend fun getSets(): MutableList<Set>? {
        val cache = localDataSource?.findAll()
        if (cache != null) return cache
        val response = remoteDataSource?.findAll()
        localDataSource?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}