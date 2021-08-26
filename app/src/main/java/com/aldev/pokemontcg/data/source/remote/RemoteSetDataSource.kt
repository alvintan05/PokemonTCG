package com.aldev.pokemontcg.data.source.remote

import com.aldev.pokemontcg.data.source.DataSource
import com.aldev.pokemontcg.data.source.remote.response.Set

class RemoteSetDataSource(private val service: ApiRequest) : DataSource<Set> {
    override suspend fun findAll(): MutableList<Set>? {
        val response = service.getAllSets()
        if (response.isSuccessful) return response.body()?.data

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<Set>?) {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorite(model: Set?) {
        TODO("Not yet implemented")
    }
}