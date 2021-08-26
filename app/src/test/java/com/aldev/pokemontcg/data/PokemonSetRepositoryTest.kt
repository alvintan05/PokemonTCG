package com.aldev.pokemontcg.data

import com.aldev.pokemontcg.data.source.DataSource
import com.aldev.pokemontcg.data.source.PokemonCardDataSource
import com.aldev.pokemontcg.data.source.remote.response.Set
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PokemonSetRepositoryTest {
    @Mock
    val localSetDataSource: DataSource<Set>? = null

    @Mock
    val remoteSetDataSource: DataSource<Set>? = null

    var pokemonSetRepository: PokemonSetRepository? = null

    var pokemonSets = mutableListOf<Set>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonSetRepository = PokemonSetRepository.instance.apply {
            init(localSetDataSource!!, remoteSetDataSource!!)
        }
    }

    @Test
    fun shouldNotGetPokemonFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localSetDataSource?.findAll()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteSetDataSource, never())?.findAll()
            verify(localSetDataSource, never())?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldCallGetPokemonFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localSetDataSource?.findAll()).thenReturn(null)
            `when`(remoteSetDataSource?.findAll()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteSetDataSource, times(1))?.findAll()
            verify(localSetDataSource, times(1))?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localSetDataSource?.findAll()).thenReturn(null)
            `when`(remoteSetDataSource?.findAll()).thenAnswer { throw Exception() }

            try {
                pokemonSetRepository?.getSets()
            } catch (ex: Exception) {

            }
        }
    }
}