package com.aldev.pokemontcg

import android.app.Application
import com.aldev.pokemontcg.data.PokemonCardRepository
import com.aldev.pokemontcg.data.PokemonSetRepository
import com.aldev.pokemontcg.data.source.local.LocalCardDataSource
import com.aldev.pokemontcg.data.source.local.LocalSetDataSource
import com.aldev.pokemontcg.data.source.remote.RemoteCardDataSource
import com.aldev.pokemontcg.data.source.remote.RemoteSetDataSource
import com.aldev.pokemontcg.data.source.remote.RetrofitServer

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonService = RetrofitServer.getService()

        PokemonSetRepository.instance.apply {
            init(LocalSetDataSource(), RemoteSetDataSource(pokemonService))
        }

        PokemonCardRepository.instance.apply {
            init(LocalCardDataSource(), RemoteCardDataSource(pokemonService))
        }
    }
}