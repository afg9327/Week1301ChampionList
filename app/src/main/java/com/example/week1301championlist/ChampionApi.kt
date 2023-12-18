package com.example.week1301championlist

import retrofit2.http.GET

interface ChampionApi {
    @GET("champion")
    suspend fun getChampions(): List<Champion>
}