package com.aldev.pokemontcg.data.source.remote

import com.aldev.pokemontcg.data.source.remote.response.CardResponse
import com.aldev.pokemontcg.data.source.remote.response.SetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("sets")
    suspend fun getAllSets(): Response<SetResponse>

    @GET("cards")
    suspend fun getCarsBySetsId(@Query("q") set: String): Response<CardResponse>

}