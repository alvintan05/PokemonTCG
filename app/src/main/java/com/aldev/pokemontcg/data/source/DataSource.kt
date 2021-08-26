package com.aldev.pokemontcg.data.source

interface DataSource<Model> {
    suspend fun findAll(): MutableList<Model>?
    suspend fun addAll(models: MutableList<Model>?)
    suspend fun addToFavorite(model: Model?)
}