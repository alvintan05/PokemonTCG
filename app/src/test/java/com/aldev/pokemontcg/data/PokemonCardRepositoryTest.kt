package com.aldev.pokemontcg.data

import com.aldev.pokemontcg.data.source.PokemonCardDataSource
import com.aldev.pokemontcg.data.source.remote.response.Set
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PokemonCardRepositoryTest {
    @Mock
    val localCardDataSource: PokemonCardDataSource? = null

    @Mock
    val remoteCardDataSource: PokemonCardDataSource? = null

    var pokemonCardRepository: PokemonCardRepository? = null

    var pokemonSets = mutableListOf<Set>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonCardRepository = PokemonCardRepository.instance.apply {
            init(localCardDataSource!!, remoteCardDataSource!!)
        }
    }

    @Test
    fun shouldNotGetPokemonFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            Mockito.`when`(localCardDataSource?.getPokemons(anyString())).thenReturn(mutableListOf())
            pokemonCardRepository?.getPokemons(anyString())

            Mockito.verify(remoteCardDataSource, Mockito.never())?.getPokemons(anyString())
            Mockito.verify(localCardDataSource, Mockito.never())?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldCallGetPokemonFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            Mockito.`when`(localCardDataSource?.getPokemons(anyString())).thenReturn(null)
            Mockito.`when`(remoteCardDataSource?.getPokemons(anyString())).thenReturn(any())
            pokemonCardRepository?.getPokemons("Test set")

            Mockito.verify(remoteCardDataSource, Mockito.times(1))?.getPokemons(anyString())
            Mockito.verify(localCardDataSource, Mockito.times(1))?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            Mockito.`when`(localCardDataSource?.getPokemons(anyString())).thenReturn(null)
            Mockito.`when`(remoteCardDataSource?.getPokemons(anyString())).thenAnswer { throw Exception() }

            try {
                pokemonCardRepository?.getPokemons("Test set")
            } catch (ex: Exception) {

            }
        }
    }
}