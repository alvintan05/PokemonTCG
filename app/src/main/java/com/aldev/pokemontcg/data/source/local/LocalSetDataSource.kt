package com.aldev.pokemontcg.data.source.local

import com.aldev.pokemontcg.data.source.DataSource
import com.aldev.pokemontcg.data.source.remote.response.Set

class LocalSetDataSource : DataSource<Set> {
    private var caches = mutableListOf<Set>()

    override suspend fun findAll(): MutableList<Set>? =
        if (caches.isNotEmpty()) caches else null


    override suspend fun addAll(models: MutableList<Set>?) {
        models?.let { caches = it }
    }

    override suspend fun addToFavorite(model: Set?) {
        TODO("Not yet implemented")
    }
}